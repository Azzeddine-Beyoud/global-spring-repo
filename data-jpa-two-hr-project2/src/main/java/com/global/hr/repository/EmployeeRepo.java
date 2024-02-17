package com.global.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;



@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	List<Employee> findBySalary(Double salary , String name);
	
	List<Employee> findByDepartmentId(Long deptId);
	
	List<Employee> findByName(String name);

	//THIS IS DRIVED QUERY
	List<Employee> findByNameContainingAndDepartmentNameContaining(String empName, String deptName);
	
	//THIS IS DRIVED QUERY
	Long countByNameContainingAndDepartmentNameContaining(String empName, String deptName);

	//THIS IS DRIVED QUERY
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	Long deleteByNameContainingAndDepartmentNameContaining(String empName, String deptName);


	//THIS IS THE JPQL
	@Query(value = "select emp from Employee emp where emp.name like :empName")
	List<Employee> filter(@Param("empName") String name);
	
	// THIS IS THE SQL NATIVE
	@Query(value = "select * from hr_employees emp where emp.emp_name like :empName" , nativeQuery = true)
	List<Employee> filterNative(@Param("empName") String name);
	  
	@Query(value = "select\n"
			+ "	(select count(*) from hr_depertment) deptCount,\n"
			+ "    (select count(*) from hr_employees) empCount,\n"
			+ "    (select count(*) from sec_users) userCount", nativeQuery = true)
	HRStatisticProjection getHRStatistic();
//	List<Employee> findByDepartmentId(Long deptId);
//	
//	@Query(value = "select emp from Employee emp join emp.department dept where dept.id = :deptId")
//	List<Employee> findByDepartment(Long deptId);

}
