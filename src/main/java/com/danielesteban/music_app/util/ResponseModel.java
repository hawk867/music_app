package com.danielesteban.music_app.util;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResponseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime date;
	private String data;
	private String message;
	
	
	public ResponseModel(LocalDateTime date, String data, String message) {
		this.date = date;
		this.data = data;
		this.message = message;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
