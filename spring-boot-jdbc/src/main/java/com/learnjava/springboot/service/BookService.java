package com.learnjava.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.learnjava.springboot.repository.Book;
import com.learnjava.springboot.repository.BookRepository;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	
	//@Autowired
	private BookValidatorService bookValidatorService;
	
	
	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books.stream().filter( (book) -> bookValidatorService.isValid(book)).toList();
	}


	@Autowired
	public void setBookValidatorService(BookValidatorService bookValidatorService) {
		this.bookValidatorService = bookValidatorService;
	}

	@Autowired
	@Qualifier("namedParameterJdbcBookRepository")
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	
	
}
