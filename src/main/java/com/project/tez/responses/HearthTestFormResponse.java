package com.project.tez.responses;

import lombok.Data;

@Data
public class HearthTestFormResponse {

	private int result;
	private String message;
	
	public HearthTestFormResponse(int result, String message) {
		this.result = result;
		this.message = message;
	}
	
}
