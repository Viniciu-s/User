package com.main.crud.exceptions;

public class UserNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
        super(message);
    }
}