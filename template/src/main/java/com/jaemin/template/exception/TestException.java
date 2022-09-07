package com.jaemin.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad")
public class TestException extends RuntimeException {

	public TestException() {
		super();
	}

	public TestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TestException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestException(String message) {
		super(message);
	}

	public TestException(Throwable cause) {
		super(cause);
	}
	
}
