package com.global.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Auther;
import com.global.book.entity.AutherSearch;
import com.global.book.service.AutherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Tag(name = "Auther controller")
@Validated
@RestController
@RequestMapping("/auther")
public class AutherController {


	private AutherService autherService;

	public AutherController(AutherService autherService) {
		super();
		this.autherService = autherService;
	}
	
	@Operation(summary = "Get a Auther by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found the Auther",
					content = { @Content(mediaType = "application/json",
					schema = @Schema(implementation = Auther.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Auther not found", content = @Content)})
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@Parameter(example = "20") @PathVariable @Min(value = 1) @Max(value = 200) Long id) {
		
		return ResponseEntity.ok(autherService.findById(id));
	}
	
	@Operation(summary = "Get a Auther by its email")
	@GetMapping("/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		
		return ResponseEntity.ok(autherService.findByEmail(email));
	}
	
	@Operation(summary = "Get all Authers")
	@GetMapping()
	public ResponseEntity<?> findAll(){
		
		return ResponseEntity.ok( autherService.findAll());
	}
	
	@Operation(summary = "add Auther")
	@PostMapping()
	public ResponseEntity<?> insert(@RequestBody @Valid Auther entity) {
//		if(entity.getId() != null) {
//			
//			throw new RuntimeException();
//		}
		return ResponseEntity.ok( autherService.insert(entity));
		
	}
	@Operation(summary = "Update Auther")
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody @Valid Auther entity) {

		return ResponseEntity.ok(autherService.update(entity));
	}	
	
	@Operation(summary = "Delete Auther")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		autherService.deleteById(id);
		return ResponseEntity.ok(null);
		
	}
	
	@Operation(summary = "Get a Auther by search")
	@PostMapping("/spec")
	public ResponseEntity<?> findByAutherSpec(@RequestBody AutherSearch search) {
		
		return ResponseEntity.ok(autherService.findByAutherSpec(search));
		
	}
}
