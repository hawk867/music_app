package com.danielesteban.music_app.controller.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.danielesteban.music_app.util.ErrorsModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorsModel handleValidationExceptions (MethodArgumentNotValidException ex) {
		  Map<String, String> errors = new HashMap<>();
		  ex.getBindingResult().getAllErrors().forEach(e -> {
			  String fieldName = ((FieldError) e).getField();
			  String message = e.getDefaultMessage();
			  errors.put(fieldName, message);
		  });
		  return new ErrorsModel( LocalDateTime.now(),  HttpStatus.BAD_REQUEST, errors);
	}
}
