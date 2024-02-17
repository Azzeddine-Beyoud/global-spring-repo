package com.global.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;
import com.global.hr.projection.EmployeeProjection;
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
	
	
	public List<Employee> filter(String name, boolean isAsc, String sortCol){
		
		if(name.isEmpty() || name.isBlank() || name==null) {
			name = null;
		}
		
		return employeeRepo.filter(name, Sort.by(isAsc ? Direction.ASC : Direction.DESC , sortCol));
	}
	
	public Page<Employee> filterPageable(String name,int pageNum, int pageSize, boolean isAsc, String sortCol){
		
		if(name.isEmpty() || name.isBlank() || name==null) {
			name = null;
		}
		
		//sort object with list of order objects
		List<Order> orders = new ArrayList<Order>();
		
//		Order order1 = new Order(isAsc ? Direction.ASC : Direction.DESC , sortCol);
//		orders.add(order1);
//		Order order2 = new Order(isAsc ? Direction.ASC : Direction.DESC , "title");
//		orders.add(order2);
		
		Pageable page = PageRequest.of(pageNum, pageSize,Sort.by(isAsc ? Direction.ASC : Direction.DESC , sortCol));
		
		return employeeRepo.filterPage(name, page);
	}

	//This the first method of projection that use EmployeeProjection Interface
//	public Page<EmployeeProjection> filterPageable(String name,int pageNum, int pageSize, boolean isAsc, String sortCol){
//		
//		if(name.isEmpty() || name.isBlank() || name==null) {
//			name = null;
//		}
//		
//		//sort object with list of order objects
//		List<Order> orders = new ArrayList<Order>();
//		
////		Order order1 = new Order(isAsc ? Direction.ASC : Direction.DESC , sortCol);
////		orders.add(order1);
////		Order order2 = new Order(isAsc ? Direction.ASC : Direction.DESC , "title");
////		orders.add(order2);
//		
//		Pageable page = PageRequest.of(pageNum, pageSize,Sort.by(isAsc ? Direction.ASC : Direction.DESC , sortCol));
//		
//		return employeeRepo.filterPage(name, page);
//	}
	
	public 	List<Employee> findByEmpAndDept(String empName, String deptName){
		
		return employeeRepo.findByFirstNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	
	public 	Long countByEmpAndDept(String empName, String deptName){
		
		return employeeRepo.countByFirstNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	
	public 	Long deleteByEmpAndDept(String empName, String deptName){
		
		return employeeRepo.deleteByFirstNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	
	public Employee insert(Employee emp) {
		if(emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			
			emp.setDepartment(departmentService.findById(emp.getDepartment().getId()));
		}
		
		return employeeRepo.save(emp);
	}
	
	public Employee update(Employee emp) {
		
		Employee currentEmp = employeeRepo.findById(emp.getId()).orElseThrow();
		
		currentEmp.setFirstName(emp.getFirstName());
		currentEmp.setLastName(emp.getLastName());

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

	
//	public List<Employee> findBySalary(Double salary, String name){
//		
//		return employeeRepo.findBySalary(salary, name);	
//	}
//	
	public HRStatisticProjection getHRStatistic() {
		
		return employeeRepo.getHRStatistic();
	}
//	public 	List<Employee> findByDepartment(Long deptId){
//		
//		return employeeRepo.findByDepartment(deptId);
//	}
}
