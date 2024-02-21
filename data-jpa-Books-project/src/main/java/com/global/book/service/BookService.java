package com.global.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Auther;
import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;


@Service
public class BookService extends BaseService<Book, Long>{

	private BookRepo bookRepo;

	public BookService(BookRepo bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	
	public Book update(Book entity) {
		
		Book book = findById(entity.getId());
		
		book.setName(entity.getName());
		
		return update(book);
	}	
	
	
	public void deleteById(Long id) {
		
		bookRepo.deleteById(id);
	}
	
	public int deleteByAutherId(Long id) {
		
		return bookRepo.deleteByAutherId(id);
	}
	
	
}
