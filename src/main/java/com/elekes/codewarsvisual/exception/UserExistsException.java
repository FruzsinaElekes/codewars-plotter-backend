package com.elekes.codewarsvisual.exception;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String username) {
        super(String.format("The following codewars username already exists: %s. Please sign in, or register with a different account", username));
    }
}
