package com.global.hr.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

	// These are Closed Projection
	Long getId();
	String getFirstName();
	String getLastName();
	
	//This is Open Projection
	@Value("#{target.firstName + ' ' + target.lastName}")
	String getFullName();
}
