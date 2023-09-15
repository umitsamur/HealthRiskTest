package com.project.tez.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.tez.entities.Forum;
import com.project.tez.entities.User;
import com.project.tez.repos.ForumRepository;
import com.project.tez.requests.ForumCreateRequest;
import com.project.tez.responses.ForumResponse;

@Service
public class ForumService {

	private ForumRepository forumRepository;
	private UserService userService;

	public ForumService(ForumRepository forumRepository, UserService userService) {
		this.forumRepository = forumRepository;
		this.userService = userService;
	}
	
	

	public List<ForumResponse> getAllForums(Optional<Long> userId) {
		List<Forum> list;
		if (userId.isPresent()) {
			list = forumRepository.findByUserId(userId.get());
		}
		else {
			list = forumRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		}
		return list.stream().map(f -> new ForumResponse(f)).collect(Collectors.toList());
		
	}

	public Forum createOneForm(ForumCreateRequest newForum) {
		User user = userService.getOneUserById(newForum.getUserId());
		if (user == null) {
			return null;
		}
		Forum toUploaded = new Forum();
		toUploaded.setId(newForum.getId());
		toUploaded.setForumName(newForum.getForumName());
		toUploaded.setMessage(newForum.getMessage());
		toUploaded.setMessageDate(new Date());
		toUploaded.setUser(user);
		return forumRepository.save(toUploaded);
	}

	
	public Forum getOneByForumId(Long forumId) {
		return forumRepository.findById(forumId).get();
	}
	
	
}
