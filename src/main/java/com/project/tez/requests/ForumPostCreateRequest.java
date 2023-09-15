package com.project.tez.requests;

import lombok.Data;

@Data
public class ForumPostCreateRequest {

	private Long userId;
	private String title;
	private String description;
	private Long forumId;
	
}
