package com.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

public class ProductNotFoundException extends HystrixBadRequestException{

	public ProductNotFoundException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1908930670854625111L;

}
