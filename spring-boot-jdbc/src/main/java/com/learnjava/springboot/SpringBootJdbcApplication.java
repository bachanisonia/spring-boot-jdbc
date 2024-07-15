package com.learnjava.springboot;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.math.BigDecimal;

import com.learnjava.springboot.repository.Book;
import com.learnjava.springboot.repository.BookRepository;

@SpringBootApplication
public class SpringBootJdbcApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);
	}
}


/*
@SpringBootApplication
public class SpringBootJdbcApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootJdbcApplication.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("namedParameterJdbcBookRepository")
	private BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start Spring Boot JDBC Application");
		runJdbc();
	}
	
	void runJdbc() {
		
		log.info("Creating book table for testing...");
		
		jdbcTemplate.execute("drop table book");
		jdbcTemplate.execute("create table book(" +
				"id bigint not null generated always as identity (start with 1, increment by 1)," +
				"name varchar(255)," +
				"price numeric)"
				);

		System.out.println("Current Book Count : " + bookRepository.count());
		
		List<Book> books = Arrays.asList(
					new Book("Thinking in Java", new BigDecimal("46.32")),
					new Book("Mkyong in Java", new BigDecimal("42.99")),
					new Book("Getting Clojure", new BigDecimal("37.3")),
					new Book("Head First Android Development", new BigDecimal("41.19")),
					new Book("Head First Android Development1", new BigDecimal("42.19"))
				);
		
		log.info("Inserting book records in the table...");
		books.forEach(
				(book) -> {
					log.info("Saving book {}", book.getName());
					bookRepository.save(book);
				}
			);
		
		books = bookRepository.findAll();
		books.forEach(
				(book) -> System.out.println("Book : " + book.getId() + "|" + book.getName())
				);
		
		log.info("Book count after insertion : " + bookRepository.count());
		
		log.info("Book Name for ID-2 : " + bookRepository.getNameById(2L));
		
		log.info("Find books for java that are less that 50 pounds : ");  
		books = bookRepository.findByNameAndPrice("Java", new BigDecimal(50));
		
		books.forEach(
				(book) -> System.out.println( "Book : " + book.getName() + "|" + book.getPrice() )
				);
		
		Book book = bookRepository.findById(2L).orElseThrow(IllegalArgumentException::new);
	
		book.setName("Generics in Java");
		book.setPrice(new BigDecimal(50));
		
		log.info("Updated book ID 2 with following details");
		bookRepository.update(book);
		log.info("Book : " + book.getName() + "|" + book.getPrice());
				
		
		log.info("Deleting the last book in the table...");
		bookRepository.deleteById(5L);
		
		log.info("Let's check all the books we have now : ");
		
		books = bookRepository.findAll();
		books.forEach(
				(book1) -> System.out.println("Book : " + book1.getId() + "|" + book1.getName() + "|" + book1.getPrice())
				);
		
	}

}
*/