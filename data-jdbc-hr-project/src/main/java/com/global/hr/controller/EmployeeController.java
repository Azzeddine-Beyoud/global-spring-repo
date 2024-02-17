package com.global.hr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;
import com.global.hr.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
//	@Autowired
//	private EmployeeRepo employeeRepo;
	
	@Autowired
	private EmployeeService employeeService;

//	@GetMapping(" /count")
	@RequestMapping(method = RequestMethod.GET, path = "/count")
	public long countEmp() {
		
	 	return employeeService.count();
	}
	
	
	
//	@GetMapping("/{id}")
//	public Employee findById(@PathVariable Long id) {
//		
//	 	return employeeRepo.findById(id).get();
//	}
//	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
	 	return ResponseEntity.ok(employeeService.findById(id)) ;
//		return new ResponseEntity(employeeRepo.findById(id).get(), HttpStatus.OK) ;
	 	
	 	// this two line of return and ResponseEntity Class for change the status of response, 
		// it works like the first findByid method but with change the response status
	}
	
	@GetMapping("")
	public Iterable<Employee> findAll() {
		
	 	return employeeService.findAll();
	}
	

	@PostMapping("")
	public Employee addEmp(@RequestBody Employee emp) {
		
//	 	return employeeRepo.save(new Employee(null,"abdo", 6645.3));
	 	return employeeService.insert(emp);

	}
	
	@PutMapping("")
	public Employee updateEmp(@RequestBody Employee emp) {
		
//	 	return employeeRepo.save(new Employee(50L,"souhila", 6645.3));
	 	return employeeService.update(emp);

	}
	
	
//	@DeleteMapping("/{id}")
//	public void deleteEmp(@PathVariable Long id) {
//		
//		employeeRepo.deleteById(id);
//	}
	
	
	@DeleteMapping("/{empId}")
	public void deleteEmp_test_path_variable_how_it_work(@PathVariable(name = "empId") Long id) {
		
		employeeService.deleteById(id);

		// pathVariable hiya bah narbto mabin wach naktbo fal path w mabin wach kayan parameter fal method, 
		// psq momkin maykonoch b nafs l asam kima darna fal exp hada 		
	}
	
	@GetMapping("/filter/{name}")
	public List<Employee> filter(@PathVariable String name) {
		
	 	return employeeService.findByName(name);
	}

//	@GetMapping("/filter/{name}/{salary}")
//	public List<Employee> filter(@PathVariable String name, @PathVariable Double salary) {
//		
//	 	return employeeRepo.findByNameAndSalary(name, salary);
//	}
	
//	@GetMapping("/filter/{name}/{salary}")
//	public List<Employee> filter(@PathVariable String name, @PathVariable Double salary) {
//		
//	 	return employeeRepo.findByNameStartingWithAndSalary(name, salary);
//	}

	
	@GetMapping("/filter/{name}/{salary}")
	public List<Employee> filter(@PathVariable String name, @PathVariable Double salary) {
		
	 	return employeeService.findByNameAndSalary(name, salary);
	}
	
	@GetMapping("/filter2")
	public List<Employee> filter2(@RequestParam String name, @RequestParam Double salary) {
		 
	 	return employeeService.findByNameAndSalary(name, salary);
	 	
	 	// we can change @PathVariable with @RequestParam 
	 	// and with this annotation (@RequestParam) we can delete the path variable from @GetMapping
	 	// this is the second or the other way to send the Parameter
	 	// with @RequestParam we don't send The PathVariable in postman but we send the Query Parameter
	}
	
	@PutMapping("/salary")
	public int updateSalary(@RequestParam Double salary,@RequestParam Long id) {
		
		return employeeService.updateSalary(salary, id);
	}
	
	
	
	public void testJackson() throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\n"
				+ "        \"employeeId\": 60,\n"
				+ "        \"name\": \"Aflah\",\n"
				+ "        \"salary\": 5340.0\n"
				+ "    		}";
		
		//Object to JSON conversion
		Employee empObject = mapper.readValue(jsonString, Employee.class);
		
		//Object to JSON conversion
		jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(empObject);

	}
	
	
}
