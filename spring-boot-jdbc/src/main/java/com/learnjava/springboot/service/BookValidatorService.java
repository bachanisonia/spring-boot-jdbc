package com.learnjava.springboot.service;

import com.learnjava.springboot.repository.Book;

public interface BookValidatorService {

	public boolean isValid(Book book);
}
