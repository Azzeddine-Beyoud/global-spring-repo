 package com.global.book.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.book.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PostLoad;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@NamedStoredProcedureQuery(name = "Book.getBookByAuther", procedureName = "GET_TOTAL_CARS_BY_MODEL", parameters = {
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "auther_id_in", type = String.class),
	@StoredProcedureParameter(mode = ParameterMode.OUT, name = "book_count", type = Integer.class)})

@Entity
@Table(name = "books")
public class Book extends BaseEntity<Long>{
	
//	@NotNull(message = "Should be enter book name")
	@NotBlank
	private String name;
	
	
	@Min(value = 5)
	@Max(value = 500)
	private double price;
	
	@Transient  /* for tell the JPA, we don't need this variable like a column in database, this variable for just calculate*/
	private double discounte;
	

	@Formula("(select count(*) from books)") /* this is like a subQuery, put them between brackets (write Native query with Formula)*/
	private long bookCount; 
	
	@NotNull
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="auther_id")
	private Auther auther;
	

	@Override
	public String toString() {
		return "Book [id=" + getId() +"name=" + name + ", price=" + price + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Auther getAuther() {
		return auther;
	}

	public void setAuther(Auther auther) {
		this.auther = auther;
	}

	public double getDiscounte() {
		return discounte;
	}

	public void setDiscounte(double discounte) {
		this.discounte = discounte;
	}
	
	@PostLoad /* for count the discounte before get the entity from database (this annotation work with @Transient)*/
	public void calcDiscounte() {
		
		this.setDiscounte(price * .25);
	}

	public long getBookCount() {
		return bookCount;
	}

	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}
	

}
