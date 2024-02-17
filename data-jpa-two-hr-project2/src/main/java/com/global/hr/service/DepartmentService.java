package com.global.hr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.repository.DepartmentRepo;


@RestController
@Service
public class DepartmentService {

	@Autowired
	public DepartmentRepo departmentRepo;
	
	public Department findById(Long id) {
		
		return departmentRepo.findById(id).orElseThrow();	
	}
	
	public Department insert(Department dept) {
		
		return departmentRepo.save(dept);
	}
	
	public Department update(Department dept) {
		
		Department currentdep = departmentRepo.findById(dept.getId()).orElseThrow();

		currentdep.setName(dept.getName());
	
		return departmentRepo.save(currentdep);
	}
	
	public List<Department> findAll(){
		
		return departmentRepo.findAll();
	}


}
