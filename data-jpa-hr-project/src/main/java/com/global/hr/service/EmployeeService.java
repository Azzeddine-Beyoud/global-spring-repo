package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

@RestController
@Service
public class EmployeeService {

	@Autowired
	public EmployeeRepo employeeRepo;
	
	@Autowired
	public DepartmentService departmentService;
	
	public Employee findById(Long id) {
		
		return employeeRepo.findById(id).orElseThrow();
		
	}
	
	public List<Employee> filter(String name){
		
		return employeeRepo.filterNative(name);
	}
	
	public 	List<Employee> findByEmpAndDept(String empName, String deptName){
		
		return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	
	public 	Long countByEmpAndDept(String empName, String deptName){
		
		return employeeRepo.countByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	
	public 	Long deleteByEmpAndDept(String empName, String deptName){
		
		return employeeRepo.deleteByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	
	public Employee insert(Employee emp) {
		if(emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			
			emp.setDepartment(departmentService.findById(emp.getDepartment().getId()));
		}
		
		return employeeRepo.save(emp);
	}
	
	public Employee update(Employee emp) {
		
		Employee currentEmp = employeeRepo.findById(emp.getId()).orElseThrow();
		
		currentEmp.setName(emp.getName());
		currentEmp.setSalary(emp.getSalary());
		currentEmp.setDepartment(emp.getDepartment());
		
		return employeeRepo.save(currentEmp);
	}

	  
	public List<Employee> findByDepartmentId(Long deptId){
		
		return employeeRepo.findByDepartmentId(deptId);
	}

	
	public List<Employee> findAll(){
		
		return employeeRepo.findAll();
	}

	
	public List<Employee> findBySalary(Double salary, String name){
		
		return employeeRepo.findBySalary(salary, name);	
	}
	
	public HRStatisticProjection getHRStatistic() {
		
		return employeeRepo.getHRStatistic();
	}
//	public 	List<Employee> findByDepartment(Long deptId){
//		
//		return employeeRepo.findByDepartment(deptId);
//	}
}
