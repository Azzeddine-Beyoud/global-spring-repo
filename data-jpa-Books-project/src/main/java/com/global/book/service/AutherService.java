package com.global.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Auther;
import com.global.book.repository.AutherRepo;

@Service
public class AutherService extends BaseService<Auther, Long> {

//	private AutherRepo autherRepo;
//
//	public AutherService(AutherRepo autherRepo) {
//		super();
//		this.autherRepo = autherRepo;
//	}

	
	@Override
	public Auther update(Auther entity) {
	
		Auther auther = findById(entity.getId());
		auther.setName(entity.getName());
		return super.update(auther);
	}
	 
	
	
	
}
