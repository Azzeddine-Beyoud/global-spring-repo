package com.global.book.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Auther;
import com.global.book.entity.AutherSearch;
import com.global.book.error.DaplicateRecoredException;
import com.global.book.repository.AutherRepo;
import com.global.book.repository.AutherSpec;

@Service
public class AutherService extends BaseService<Auther, Long> {

	private AutherRepo autherRepo;

	public AutherService(AutherRepo autherRepo) {
		super();
		this.autherRepo = autherRepo;
	}
	
	Logger log = LoggerFactory.getLogger(AutherService.class);
	
	@Override
	public Auther insert(Auther entity) {
		
		if(!entity.getEmail().isEmpty() && entity.getEmail() != null) {
			
//			Optional<Auther> auther = findByEmail(entity.getEmail());
			CompletableFuture<Auther> auther = findByEmail(entity.getEmail());

			log.info("author name is {} and email is {}", entity.getName(), entity.getEmail());
			
//			   auther.isPresent()
			if(auther.isDone()) {
				log.error("This email already found with another Auther");
				throw new DaplicateRecoredException("This email already found with another Auther");
			}
		} 
		return super.insert(entity);
	}
	@Override
	public Auther update(Auther entity) {
	
		Auther auther = findById(entity.getId());
		auther.setName(entity.getName());
		return super.update(auther);
	}
	 
	public List<Auther> findByAutherSpec(AutherSearch search){
		
		AutherSpec spec = new AutherSpec(search);
		
		return autherRepo.findAll(spec);
	}
	
	
//	private Optional<Auther> findByEmail(String email){
//		
//		return autherRepo.findByEmail(email);
//	}
	
	@Async(value = "threadPoolTaskExecutor")
	public CompletableFuture<Auther> findByEmail(String email){
		
		return CompletableFuture.completedFuture(autherRepo.findByEmail(email).get());
	}
	
}
