package com.global.book.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.book.entity.PostDto;


@Service
public class PostService {

	private static String POST_URL = "https://jsonplaceholder.typicode.com/posts";
	
	public PostDto getPostById(Long id) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PostDto> result = restTemplate.getForEntity(POST_URL+"/"+id, PostDto.class);
		
		return result.getBody();
	}
	
	public List<PostDto> getAllPosts() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List> result = restTemplate.getForEntity(POST_URL, List.class);
		
		return result.getBody();
	}
	
	
	public PostDto addPost(PostDto dto) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("accept", "application/json");
		headers.add("accept-language", "en");
		
		HttpEntity<PostDto> request = new HttpEntity<>(dto, headers);
		
		ResponseEntity<PostDto> result = restTemplate.postForEntity(POST_URL, request, PostDto.class);
		
		return result.getBody();
	}
	
	public void updatePost(PostDto dto) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<PostDto> request = new HttpEntity<>(dto);
		
		restTemplate.put(POST_URL, request);
	}
	

	public void deletePostById(Long id) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(POST_URL+"/"+id);
	}
}
