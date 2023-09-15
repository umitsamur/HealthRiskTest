package com.project.tez.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tez.entities.ForumPost;
import com.project.tez.requests.ForumPostCreateRequest;
import com.project.tez.responses.ForumPostResponse;
import com.project.tez.services.ForumPostService;

@RestController
@RequestMapping("/forumpost")
public class ForumPostController {

	private ForumPostService forumPostService;

	public ForumPostController(ForumPostService forumPostService) {
		this.forumPostService = forumPostService;
	}
	
	@PostMapping
	public ForumPost createOnePost(@RequestBody ForumPostCreateRequest request) {
		return forumPostService.createOnePost(request);
	}
	
	@GetMapping("/{forumId}") 
	public List<ForumPostResponse> getForumPosts(@PathVariable Long forumId,@RequestParam("userId") Long userId){
		return forumPostService.getForumPosts(forumId,userId);
	}
	
	
}
