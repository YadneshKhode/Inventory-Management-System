package com.accenture.lkm.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomJsonParsingUtils {
	
	private static final ObjectMapper mapper =  new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	static public Object covertFromJsonToObject1(String json, @SuppressWarnings("rawtypes") Class var) throws IOException{
		return mapper.readValue(json, var);//Convert Json into object of Specific Type
	}
	
	//Generic Type Safe Method
	static public <T> T covertFromJsonToObject2(String json, Class<T> var) throws IOException{
		return mapper.readValue(json, var);//Convert Json into object of Specific Type
	}
	public static String covertFromObjectToJson(Object obj) throws JsonProcessingException{
		return mapper.writeValueAsString(obj);
	}
	

}
