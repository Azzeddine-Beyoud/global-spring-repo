package com.global.book.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.book.entity.Auther;
import com.global.book.entity.Book;
import com.global.book.service.AutherService;
import com.global.book.service.BookService;

@Component
public class StartupApp implements CommandLineRunner{

	@Autowired
	private AutherService autherService;
	
	@Autowired
	private BookService bookService;

	@Override
	public void run(String... args) throws Exception {
	
		Auther auther1 = new Auther();
		auther1.setName("Ali");
		
		Auther auther2 = new Auther();
		auther2.setName("Mohammed");
		
		Auther auther3 = new Auther();
		auther3.setName("Ahmed");
		
		autherService.insertAll(Arrays.asList(auther1, auther2, auther3));
		
		Book book1 = new Book();
		book1.setName("Inside Out");
		book1.setPrice(200);
		book1.setAuther(autherService.getReferenceById(1L));
		
		Book book2 = new Book();
		book2.setName("Power Of New");
		book2.setPrice(344);
		book2.setAuther(autherService.getReferenceById(1L));
		
		Book book3 = new Book();
		book3.setName("the End");
		book3.setPrice(344);
		book3.setAuther(autherService.findById(2L));
		
		bookService.insertAll(Arrays.asList(book1, book2, book3));
	}
	
	
}
