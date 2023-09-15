package com.project.tez.requests;


import lombok.Data;

@Data
public class ForumCreateRequest {

	private Long id;
	private String forumName;
	private String message;
	private Long userId;
	
}
