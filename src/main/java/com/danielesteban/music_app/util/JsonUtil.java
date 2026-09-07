package com.danielesteban.music_app.util;

import com.danielesteban.music_app.util.excepcion.IllegalBodyException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static String toStringJson(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error to parse json");
		}
	}
	
	public static Object bodyMapper(Object body, Class<?> typeReturn) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			String json = mapper.writeValueAsString(body);
			return mapper.readValue(json, typeReturn);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new IllegalBodyException("Error to parse Body");
		}
	}

}
