package com.project.tez.responses;

import lombok.Data;

@Data
public class RiskTestFormResponse {
	
	private int result;
	private String message;
	
	public RiskTestFormResponse(int result, String message) {
		this.result = result;
		this.message = message;
	}

	
	
}
