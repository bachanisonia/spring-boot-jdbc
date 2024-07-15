package com.learnjava.springboot.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.learnjava.springboot.repository.Book;
import com.learnjava.springboot.repository.NamedParameterJdbcBookRepository;
import com.learnjava.springboot.service.BookService;
import com.learnjava.springboot.service.BookValidatorService;
import com.learnjava.springboot.service.BookValidatorServiceImpl;

@SpringBootTest
class BookServiceTest {

	/*
	@MockBean
	private BookValidatorService bookValidatorService;
	
	@Mock
	private NamedParameterJdbcBookRepository namedParameterJdbcBookRepository;
	
	@InjectMocks
	private BookService bookService; 
	*/
	//@InjectMocks
	//private BookController bookController = new BookController(); 
	
	private BookService bookService = new BookService();
	
	@BeforeEach
	void setup() {
		
		List<Book> books = Arrays.asList( 
				new Book(1L, "Sudha Murthy", new BigDecimal(250)),
				new Book(2L, "bot", new BigDecimal(2000)),
				new Book(3L, "Preeti Shenoy", new BigDecimal(199))
			);
		
		NamedParameterJdbcBookRepository mockRepository = mock(NamedParameterJdbcBookRepository.class);
		BookValidatorServiceImpl mockValidator = mock(BookValidatorServiceImpl.class);
		//
		when(mockRepository.findAll()).thenReturn(books);
		
		bookService.setBookRepository(mockRepository);
		bookService.setBookValidatorService(new BookValidatorServiceImpl());
	
	}
	
	@Test
	void test_total_books_by_mock() {
		
			List<Book> expectedBooks = Arrays.asList(
					new Book(1L, "Sudha Murthy", new BigDecimal(250)),
					new Book(3L, "Preeti Shenoy", new BigDecimal(199))
				);
			
			List<Book> result = bookService.getAllBooks();
			result.stream().forEach(System.out::println);
			assertEquals(expectedBooks, result);
			
	}

}
