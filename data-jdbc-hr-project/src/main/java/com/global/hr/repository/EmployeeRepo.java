package com.global.hr.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;
import com.global.hr.mapper.EmployeeMapper;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long>{

	List<Employee> findByName(String name);
	
	List<Employee> findByNameStartingWith(String name);
	
//	List<Employee> findByNameAndSalary(String name, Double salary);

//	List<Employee> findByNameStartingWithAndSalary(String name, Double salary);

//	List<Employee> findByNameContainingAndSalaryGreaterThanEqual(String name, Double salary);

//	@Query(value = "SELECT id, name, salary FROM hrglobal.employee where name like :empName and salary >= :empSalary")
//	List<Employee> findByNameAndSalary(@Param("empName") String name, @Param("empSalary") Double salary);
	
	@Query(value = "SELECT id, name, salary FROM hrglobal.employee where name like :empName and salary >= :empSalary",
			rowMapperClass = EmployeeMapper.class)
	List<Employee> findByNameAndSalary(@Param("empName") String name, @Param("empSalary") Double salary);

	@Modifying
	@Query(value = "update employee set salary = :salary where id = :id")
	int updateSalary(Double salary, Long id);
}
