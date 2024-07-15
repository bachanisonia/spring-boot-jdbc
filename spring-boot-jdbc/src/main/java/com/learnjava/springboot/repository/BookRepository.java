package com.learnjava.springboot.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

	int count();
	List<Book> findAll();
	Optional<Book> findById(Long id);
	String getNameById(Long id);
	public int save(Book book);
	public int update(Book book);
	public int deleteById(Long id);
	public List<Book> findByNameAndPrice(String name, BigDecimal price);
	
}
