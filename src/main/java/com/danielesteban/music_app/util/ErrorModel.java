package com.danielesteban.music_app.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = 3375934706761730606L;
	
	private LocalDateTime date;
	private Integer code;
	private String status;
	private String error;
	
	public ErrorModel(LocalDateTime date, HttpStatus httpStatus, String error) {
		this.date = date;
		this.error = error;
		this.code = httpStatus.value();
		this.status = httpStatus.name();
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getError() {
		return error;
	}

	public String getStatus() {
		return status;
	}

	public Integer getCode() {
		return code;
	}
		
}
