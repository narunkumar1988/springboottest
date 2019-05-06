package com.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static String objectToString(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(object);
		return json;
	}

}
