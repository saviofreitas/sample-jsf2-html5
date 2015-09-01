package com.jsf22.html5.app.persistence.dao.generic;

import java.util.HashMap;
import java.util.Map;

public class QueryParameter {

	private Map<String, Object> parameters = null;

	private QueryParameter(String key, Object value) {
		this.parameters = new HashMap<String, Object>();
		this.parameters.put(key, value);
	}
	
	public static QueryParameter with(String key, Object value) {
		return new QueryParameter(key, value);
	}
	
	public QueryParameter and(String key, Object value) {
		this.parameters.put(key, value);
		return this;
	}
	
	public Map<String, Object> parameters() {
		return this.parameters;
	}
	
}
