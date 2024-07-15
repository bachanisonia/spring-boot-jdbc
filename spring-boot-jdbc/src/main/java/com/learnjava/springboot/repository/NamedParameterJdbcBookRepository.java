package com.learnjava.springboot.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NamedParameterJdbcBookRepository extends JdbcBookRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public int update(Book book) {
		String sql = "update book set name = :name, price = :price where id = :id";
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(book));
	}
	
	@Override
	public Optional<Book> findById(Long id) {           
		String sql = "select * from book where id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, 
				new MapSqlParameterSource("id", id), 
				(rs, rowNum) -> 
					Optional.of( new Book( rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price") ) )
				);
	}
	
	@Override
	public List<Book> findByNameAndPrice(String name, BigDecimal price) {
		
		String sql = "select * from book where name like :name and price <= :price";
		
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		
		mapSqlParameterSource.addValue("name", "%" + name + "%");
		mapSqlParameterSource.addValue("price", price);
		
		return namedParameterJdbcTemplate.query(
							sql, 
							mapSqlParameterSource, 
							(rs, rowNum) -> new Book( rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price") ) 
				);
	}
	
}
