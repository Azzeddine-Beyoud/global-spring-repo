package com.global.hr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FetchType;
import jakarta.persistence.FieldResult;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;


@Table(name = "hr_employees")
@Entity
@NamedQuery(name = "Employee.findBySalary", query = "select emp from Employee emp where emp.salary >= :salary and name like :name" )
@SqlResultSetMapping(name = "empMapping",
		entities = @EntityResult(
				entityClass = Employee.class,
				fields = {
						@FieldResult(name = "id", column = "emp_id"),
						@FieldResult(name = "name", column = "emp_name"),
						@FieldResult(name = "salary", column = "salary"),}) )
@NamedNativeQuery(name = "Employee.findByDepartmentId", query = "select * from hr_employees emp where department_id= :deptId",
					resultSetMapping = "empMapping")
public class Employee{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long id;
	
	@Column(name = "emp_name")
	private String name;
	 
	private Double salary;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name = "department_id")
//	@JsonIgnore
	private Department department;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
//	@Version
//	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	 
	
}
