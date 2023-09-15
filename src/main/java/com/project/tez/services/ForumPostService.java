package com.project.tez.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.tez.entities.Forum;
import com.project.tez.entities.ForumPost;
import com.project.tez.entities.UserCluster;
import com.project.tez.repos.ForumPostRepository;
import com.project.tez.requests.ForumPostCreateRequest;
import com.project.tez.responses.ForumPostResponse;

@Service
public class ForumPostService {
	private final static Logger logger = LoggerFactory.getLogger(ForumPostService.class);
	private ForumPostRepository forumPostRepository;
	private UserClusterService userClusterService;
	private ForumService forumService;
	
	public ForumPostService(ForumPostRepository forumPostRepository, UserClusterService userClusterService, ForumService forumService) {
		this.forumPostRepository = forumPostRepository;
		this.userClusterService = userClusterService;
		this.forumService = forumService;
	}

	public ForumPost createOnePost(ForumPostCreateRequest request) {
		
		logger.info("title: " + request.getTitle() + " description: " + request.getDescription() + " userId: " + request.getUserId());
		
		UserCluster userCluster = userClusterService.getOneByUserId(request.getUserId());
		Forum forum = forumService.getOneByForumId(request.getForumId());
		if(userCluster == null) {
			return null;
		}
		
		ForumPost toSave = new ForumPost();
		toSave.setTitle(request.getTitle());
		toSave.setDescription(request.getDescription());
		toSave.setUserCluster(userCluster);
		toSave.setForum(forum);
		toSave.setCreatedDate(new Date());
		
		return forumPostRepository.save(toSave);
		
	}
/*
	public List<ForumPostResponse> getForumPosts(Long forumId,Long userId) {
		UserCluster userCluster = userClusterService.findByUserId(userId);
		long clusterId = userCluster.getCluster().getId();
		List<Long> ids = forumPostRepository.getIdsByClusterId(clusterId);
		List<ForumPost> list;
		List<ForumPost> toReturnList = new ArrayList<>();
		//list = forumPostRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		list = forumPostRepository.findByUserClusterIdIn(ids);
		for (ForumPost forumPost : list) {
			if(forumPost.getForum().getId() == forumId) {
				toReturnList.add(forumPost);
			}
		}
		toReturnList.sort(Comparator.comparingLong(ForumPost::getId).reversed());
		return toReturnList.stream().map(fp -> {return new ForumPostResponse(fp);}).collect(Collectors.toList());
		
	}*/
	
	public List<ForumPostResponse> getForumPosts(Long forumId,Long userId) {
		UserCluster userCluster = userClusterService.findByUserId(userId);
		long clusterId = userCluster.getCluster().getId();
		List<Long> ids = forumPostRepository.getIdsByClusterId(clusterId);
		List<ForumPost> list;
		//list = forumPostRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		list = forumPostRepository.findByUserClusterIdIn(ids);
		
		list.sort(Comparator.comparingLong(ForumPost::getId).reversed());
		return list.stream().filter(f->f.getForum().getId() == forumId).map(fp -> {return new ForumPostResponse(fp);}).collect(Collectors.toList());
		
	}
	
	/*
	public List<ForumPostResponse> getForumPosts(Long userId) {
		UserCluster userCluster = userClusterService.findByUserId(userId);
		long clusterId = userCluster.getCluster().getId();
		List<Long> ids = forumPostRepository.getIdsByClusterId(clusterId);
		List<ForumPost> list;
		//list = forumPostRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		list = forumPostRepository.findByUserClusterIdIn(ids);
		list.sort(Comparator.comparingLong(ForumPost::getId).reversed());
		return list.stream().map(fp -> {return new ForumPostResponse(fp);}).collect(Collectors.toList());
		
	}*/
	
	public void dropTable() {
		forumPostRepository.dropTable();
	}
	

}
