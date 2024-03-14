package com.global.book;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.book.entity.Author;
import com.global.book.repo.AuthorRepo;

@Component
public class StartupApp implements CommandLineRunner {

	@Autowired
	private AuthorRepo authorRepo;
	
	@Override
	public void run(String... args) throws Exception {
	
		if(authorRepo.findAll().isEmpty()) {
			

		Author author = new Author();
		author.setName("mohamed");
		author.setEmail("moh@gmail.com");	
		author.setPhone("03433234234");
		
		Author author1 = new Author();
		author1.setName("Ali");
		author1.setEmail("ali@gmail.com");	
		author1.setPhone("0455533234234");
		
		Author author2 = new Author();
		author2.setName("azzo");
		author2.setEmail("azzo@gmail.com");	
		author2.setPhone("02673234234");
		
		authorRepo.insert(Arrays.asList(author,author1,author2));
		}
	}

}
