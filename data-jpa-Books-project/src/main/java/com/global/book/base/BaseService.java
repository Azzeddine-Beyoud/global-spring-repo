package com.global.book.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.global.book.entity.Auther;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseService<T extends BaseEntity<ID> , ID extends Number> {

	@Autowired
	private BaseRepository<T, ID> baseRepository;
	
	

	public T findById(ID id) {
		
		return baseRepository.findById(id).orElseThrow();
	}
	
	public T getReferenceById(ID id) {
		
		return baseRepository.getReferenceById(id);
	}
	
	public List<T> findAll(){
		
		return baseRepository.findAll();	
	}
	
	public T insert(T entity) {
		
		if(entity.getId() != null) {
			
			throw new RuntimeException();
		}
		return baseRepository.save(entity);
	}
	
	public List<T> insertAll(List<T> entities) {  
		
		return baseRepository.saveAll(entities);
	}
	
	public T update(T entity) {
//		T auther = findById(entity.getId());
//		auther.setName(entity.getName());
		return baseRepository.save(entity);
	}	
	
	
	public void deleteById(ID id) {
		
		baseRepository.deleteById(id);
	}
	
}
