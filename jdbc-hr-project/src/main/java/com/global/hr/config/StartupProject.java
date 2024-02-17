package com.global.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeReps;

@Component
public class StartupProject implements CommandLineRunner{

	@Autowired
	@Qualifier("employeeNameJDBCParameterRepo")
	private EmployeeReps employeeReps;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		
		 jdbcTemplate.execute("DROP TABLE IF EXISTS employees");
		 jdbcTemplate.execute("CREATE TABLE employees(id SERIAL, name VARCHAR(255), salary NUMERIC (15, 2))");
		 
		 if(employeeReps.count()== 0) {
			 employeeReps.insert(new Employee(20L, "Azzeddine", 4000.0));
			 employeeReps.insert(new Employee(30L, "Hamid", 5034.0));
			 employeeReps.insert(new Employee(40L, "Moufdi", 2400.0));
			 employeeReps.insert(new Employee(50L, "Ihab", 3200.0));
			 employeeReps.insert(new Employee(60L, "Aflah", 5340.0));
			 employeeReps.insert(new Employee(70L, "nono", 1500.0));
		 }
	}
}
