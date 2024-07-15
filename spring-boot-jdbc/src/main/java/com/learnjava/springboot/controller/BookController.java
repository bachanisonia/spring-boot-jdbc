package com.learnjava.springboot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjava.springboot.repository.Book;
import com.learnjava.springboot.service.BookService;
import com.learnjava.springboot.service.BookValidatorService;

@Controller
//@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;


	public List<Book> getAllBooks() {
		
		return bookService.getAllBooks();
		
	}
}
