package com.project.tez.responses;

import java.util.Date;

import com.project.tez.entities.Forum;

import lombok.Data;

@Data
public class ForumResponse {

	private Long id;
	private Long userId;
	private String name;
	private String surname;
	private String email;
	private String forumName;
	private String message;
	private Date messageDate;
	
	public ForumResponse(Forum entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.name = entity.getUser().getName();
		this.surname = entity.getUser().getSurname();
		this.email = entity.getUser().getEmail();
		this.forumName = entity.getForumName();
		this.message = entity.getMessage();
		this.messageDate = entity.getMessageDate();
	}
	
}
