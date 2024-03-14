package com.global.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Auther;
import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;


@Service
public class BookService extends BaseService<Book, Long>{
	
	@Autowired
	private BookRepo bookRepo;

	
//	public BookService(BookRepo bookRepo) {
//		super();
//		this.bookRepo = bookRepo;
//	}
	
	@Override
	public Book update(Book entity) {
		
		Book book = findById(entity.getId());
		
		book.setName(entity.getName());
		
		return super.update(book);
	}	
	
	
	public int deleteByAutherId(Long id) {
		
		return bookRepo.deleteByAutherId(id);
	}
//	
	
}
