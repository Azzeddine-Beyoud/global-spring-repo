package com.global.hr.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Table(name = "hr_depertment ")
@Entity
public class Department {

	@Id
	
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "depratment_gen")
//	@SequenceGenerator(name = "depratment_gen", sequenceName = "department_seq", initialValue = 100)
	
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "depratment_gen" )
//	@TableGenerator(name = "depratment_gen", table = "depratment_seq")
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	private String name ;

//	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
//	private List<Employee> employees;
	
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

//	public List<Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}
	
	
	
}
