package com.project.tez.responses;

import com.project.tez.entities.ForumPost;

import lombok.Data;

@Data
public class ForumPostResponse {

	private Long id;
	private Long userId;
	private Long clusterId;
	private Long forumId;
	private String firstname;
	private String lastname;
	private String email;
	private String title;
	private String description;
	
	public ForumPostResponse(ForumPost entity) {
		this.id = entity.getId();
		this.userId = entity.getUserCluster().getUser().getId();
		this.clusterId = entity.getUserCluster().getCluster().getId();
		this.forumId = entity.getForum().getId();
		this.firstname = entity.getUserCluster().getUser().getName();
		this.lastname = entity.getUserCluster().getUser().getSurname();
		this.email = entity.getUserCluster().getUser().getEmail();
		this.title = entity.getTitle();
		this.description = entity.getDescription();
	}
	
	
}
