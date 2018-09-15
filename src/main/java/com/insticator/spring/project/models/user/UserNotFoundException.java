package com.insticator.spring.project.models.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6543270281973716932L;

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

