package com.learnjava.springboot.repository;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {

	private Long id;
	@Override
	public int hashCode() {
		return Objects.hash(name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}
	private String name;
	private BigDecimal price;
	
	public Book() {
	}
		
	public Book(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Book(Long id, String name, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
