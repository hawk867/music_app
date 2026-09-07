package com.danielesteban.music_app.controller.handler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import com.danielesteban.music_app.util.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ErrorModel handleNoSuchElementExceptions (NoSuchElementException ex) {
		  return new ErrorModel( LocalDateTime.now(),  HttpStatus.BAD_REQUEST, ex.getMessage());
	}

}
