package com.global.hr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table("employee")         					//hr_employees (SQL Table) >> HrEmployees  (java class)
public class Employee {

	@Id
	@Column("id")
	@JsonProperty("empId")
	private Long employeeId ;
	private String name;
	private Double salary;
	
	@Transient
	@JsonIgnore
	private String isCreate;  // @transient this when we have a parameter we need them just in the class not in SQL
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(Long employeeId, String name, Double salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
	}
	
	
	public Employee(String name, Double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	public String isCreate() {
		return isCreate;
	}
	public void setCreate(String isCreate) {
		this.isCreate = isCreate;
	}
	
}
