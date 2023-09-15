package com.project.tez.requests;

import java.util.Date;

import lombok.Data;

@Data
public class UserRequest {

	private String name;
	private String surname;
	private String email;
	private String password;
	private String contactNumber;
	private Date birthDate;
	private int gender;
	private String occupation;
	
}
