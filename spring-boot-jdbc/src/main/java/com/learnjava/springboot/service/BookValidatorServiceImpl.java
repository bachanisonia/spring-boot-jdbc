package com.learnjava.springboot.service;

import org.springframework.stereotype.Component;

import com.learnjava.springboot.repository.Book;

@Component
public class BookValidatorServiceImpl implements BookValidatorService {

	@Override
	public boolean isValid(Book book) {
		
		if ("bot".equals(book.getName())) {
			return false;
		}
		else {
			return true;
		}
	}

}
