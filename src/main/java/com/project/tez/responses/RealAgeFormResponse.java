package com.project.tez.responses;

import lombok.Data;

@Data
public class RealAgeFormResponse {

	
	private int age;
	private int realAge;
	private String message;
	
	public RealAgeFormResponse(int age, int realAge, String message) {
		this.age = age;
		this.realAge = realAge;
		this.message = message;
	}
	
}
