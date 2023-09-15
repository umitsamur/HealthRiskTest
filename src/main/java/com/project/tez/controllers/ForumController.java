package com.project.tez.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tez.entities.Forum;
import com.project.tez.requests.ForumCreateRequest;
import com.project.tez.responses.ForumResponse;
import com.project.tez.services.ForumService;

@RestController
@RequestMapping("/forums")
public class ForumController {

	private ForumService forumService;

	public ForumController(ForumService forumService) {
		this.forumService = forumService;
	}
	
	@GetMapping
	public List<ForumResponse> getAllForums(@RequestParam Optional<Long> userId){
		return forumService.getAllForums(userId);
	}
	
	@PostMapping
	public Forum createOneForum(@RequestBody ForumCreateRequest newForum) {
		return forumService.createOneForm(newForum);
	}
	
}
