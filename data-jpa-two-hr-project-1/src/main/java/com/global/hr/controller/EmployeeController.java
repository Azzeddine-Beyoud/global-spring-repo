package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.entity.EmployeeResponse;
import com.global.hr.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService ;
	
//	@GetMapping("/{id}")
//	public Employee findById(@PathVariable Long id) {
//		
//		return employeeService.findById(id);	
//	}
	
	@GetMapping("/{id}")
	public EmployeeResponse findById(@PathVariable Long id) {
		
		Employee emp = employeeService.findById(id);
		EmployeeResponse res = new EmployeeResponse();
		
		res.setId(emp.getId());
		res.setName(emp.getFirstName());
		res.setSalary(emp.getSalary());
		res.setDepartment(emp.getDepartment());
		res.setUser(emp.getUser());
		
		return res;	
	}
	
	@GetMapping("/emp-dept")
	public 	List<Employee> findByEmpAndDept(@RequestParam String empName, @RequestParam String deptName){
		
		return employeeService.findByEmpAndDept(empName, deptName);
	}
	
	@GetMapping("/count-emp-dept")
	public ResponseEntity<Long> countByEmpAndDept(String empName, String deptName){
		
		return ResponseEntity.ok(employeeService.countByEmpAndDept(empName, deptName));
	}
	
	@DeleteMapping("/emp-dept")
	public ResponseEntity<Long> deleteByEmpAndDept(String empName, String deptName){
		
		return ResponseEntity.ok(employeeService.deleteByEmpAndDept(empName, deptName));
	}
	
	@GetMapping()
	public  List<Employee> findAll() {
		
		return  employeeService.findAll();
	}
	

	@GetMapping("/filter/sorting")
	public List<Employee> findByName(@RequestParam String name, @RequestParam Boolean isAsc, @RequestParam String sortCol){
		
		return employeeService.filter(name, isAsc, sortCol);
	}

	@GetMapping("/filter/pageable")
	public ResponseEntity<?> findByNamePage(@RequestParam String name, @RequestParam int pageNum, @RequestParam int pageSize,
										@RequestParam Boolean isAsc, @RequestParam String sortCol){
		
		return ResponseEntity.ok(employeeService.filterPageable(name, pageNum, pageSize, isAsc, sortCol));
	}

	
	@PostMapping()
	public Long insert(@RequestBody Employee emp) {
		
		return employeeService.insert(emp).getId(); 
	}
	
	@PutMapping()	
	public Employee update(@RequestBody Employee emp) {
		
		return employeeService.update(emp);
	}
	
//	@GetMapping("/salary")
//	public ResponseEntity<?> findBySalary(@RequestParam Double salary, @RequestParam String name){
//		
//		return ResponseEntity.ok(employeeService.findBySalary(salary, name));	
//	}
	

	@GetMapping("/department/{deptId}")
	public List<Employee> findByDepartmentId(@PathVariable Long deptId){
		
		return employeeService.findByDepartmentId(deptId);
	}
	
	@GetMapping("/statistic")
	public ResponseEntity<?>  getHRStatistic() {
		
		return ResponseEntity.ok(employeeService.getHRStatistic());
	}
}
