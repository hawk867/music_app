package com.danielesteban.music_app.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ErrorsModel implements Serializable {

	private static final long serialVersionUID = 3375934706761730606L;
	
	private LocalDateTime date;
	private Integer code;
	private String status;
	private Map<String, String> errors;
	
	public ErrorsModel(LocalDateTime date, HttpStatus httpStatus, Map<String, String> errors) {
		this.date = date;
		this.errors = errors;
		this.code = httpStatus.value();
		this.status = httpStatus.name();
	}

	public LocalDateTime getDate() {
		return date;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getStatus() {
		return status;
	}

	public Integer getCode() {
		return code;
	}
		
}
