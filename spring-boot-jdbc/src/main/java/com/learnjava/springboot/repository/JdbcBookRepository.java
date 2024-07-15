package com.learnjava.springboot.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBookRepository implements BookRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from book", Integer.class);
	}
	

	@Override
	public List<Book> findAll() {
		return jdbcTemplate.query( 
				"select * from book",
				(rs, rowNum) -> 
					new Book(
							rs.getLong("id"),
							rs.getString("name"),
							rs.getBigDecimal("price")
				));
	}

	@Override
	public Optional<Book> findById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from book where id = ?",
				(rs, rowNum) -> 
					Optional.of( new Book(rs.getLong("id"),
							rs.getString("name"),
							rs.getBigDecimal("price")
						)), 
					id
				);
	}

	@Override
	public String getNameById(Long id) {
		String sql = "select name from book where id = ?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}

	@Override
	public int save(Book book) {
		String sql = "insert into book(name, price) values(?,?)";
		return jdbcTemplate.update(sql, book.getName(), book.getPrice());
	}

	@Override
	public int update(Book book) {
		String sql = "update book set name = ?, price = ? where id = ?";
		return jdbcTemplate.update(sql, book.getName(), book.getPrice(), book.getId());
	}

	@Override
	public int deleteById(Long id) {
		String sql = "delete from book where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Book> findByNameAndPrice(String name, BigDecimal price) {
		String sql = "select * from book where name like ? and price <= ?";
		return jdbcTemplate.query(
						sql, 
						(rs, rowNum) -> 
							new Book(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price")), 
						"%"+name+"%", price);
	}
	
	

}
