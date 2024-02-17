package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Role;
import com.global.hr.repository.RoleRepo;

@RestController
@Service
public class RoleService {

	@Autowired
	public RoleRepo roleRepo;
	
	public Role findById(Long id) {
		
		return roleRepo.findById(id).orElseThrow();
		
	}
	
	
	public Role insert(Role role) {
		
		return roleRepo.save(role);
	}
	
	public Role update(Role role) {
		
		Role currentRole = roleRepo.findById(role.getId()).orElseThrow();		
		currentRole.setName(role.getName());
		
		return roleRepo.save(currentRole);
	}

	
	public List<Role> findAll(){
		
		return roleRepo.findAll();
	}

	public Role findByName(String name) {
		
		return roleRepo.findByName(name);
	}
}
