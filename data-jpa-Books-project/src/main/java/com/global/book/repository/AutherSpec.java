package com.global.book.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.global.book.entity.Auther;
import com.global.book.entity.AutherSearch;
import com.global.book.entity.Book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AutherSpec implements Specification<Auther>{

//	private String autherName;
//	public AutherSpec(String autherName) {
//		super();
//		this.autherName = autherName;
//	}
	private AutherSearch search;
	

	public AutherSpec(AutherSearch search) {
		super();
		this.search = search;
	}

	@Override
	public Predicate toPredicate(Root<Auther> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
 
		List<Predicate> predicates = new ArrayList<>();
		
		Join<Auther,Book> bookJoin = root.join("books", JoinType.LEFT);
		
		// auther name
		if(search.getAutherName() != null && !search.getAutherName().isEmpty()) {
			
			predicates.add(cb.like(root.get("name"), search.getAutherName()));	
		}

		// auther email
		if(search.getEmail() != null && !search.getEmail().isEmpty()) {
			
			predicates.add(cb.like(root.get("email"), search.getEmail()));
		}
		
		// auther ipAddress
		if(search.getIpAddress() != null && !search.getIpAddress().isEmpty()) {
			
			predicates.add(cb.like(root.get("ipAddress"), "%" + search.getIpAddress() + "%" ));
		}
		
		// auther BookJoin
		if(search.getBookName() != null && !search.getBookName().isEmpty()) {
			
			predicates.add(cb.like(bookJoin.get("name"),search.getBookName()));
		}
		
		// auther Book price
		if(search.getPrice() != null) {
			
			predicates.add(cb.lessThanOrEqualTo(bookJoin.get("price"),search.getPrice()));
		}
		
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
