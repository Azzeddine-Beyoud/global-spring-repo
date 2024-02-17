package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity. Department;
import com.global.hr.entity.Employee;
import com.global.hr.service. DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	public  DepartmentService  departmentService ;
	
	@GetMapping("/{id}")
	public  Department findById(@PathVariable Long id) {
		
		return  departmentService.findById(id);
	}

	
	@GetMapping("")
	public  List<Department> findAll() {
		
		return  departmentService.findAll();
	}
	
	@PostMapping()
	public Department insert(@RequestBody Department dept) {
		
		return departmentService.insert(dept);
	}
	
	@PutMapping()
	public Department update(@RequestBody Department dept) {
	
		return departmentService.update(dept);
	}
}
