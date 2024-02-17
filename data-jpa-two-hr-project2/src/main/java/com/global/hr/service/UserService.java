package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.User;
import com.global.hr.repository.UserRepo;

@RestController
@Service
public class UserService {

	@Autowired
	public UserRepo userRepo;
	
	public User findById(Long id) {
		
		return userRepo.findById(id).orElseThrow();
		
	}
	
	
	public User insert(User user) {
		
		return userRepo.save(user);
	}
	
	public User update(User user) {
		
		User currentUser = userRepo.findById(user.getId()).orElseThrow();
		
		currentUser.setUserName(user.getUserName());
		currentUser.setPassword(user.getPassword());
		
		return userRepo.save(currentUser);
	}

	
	public List<User> findAll(){
		
		return userRepo.findAll();
	}

}
