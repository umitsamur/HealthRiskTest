package com.project.tez.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tez.entities.Cluster;
import com.project.tez.entities.User;
import com.project.tez.entities.UserCluster;
import com.project.tez.repos.UserClusterRepository;

@Service
public class UserClusterService {
	
	private UserClusterRepository userClusterRepository;
	
	private RiskFormService riskFormService;
	private HearthTestService hearthTestService;
	private RealAgeService realAgeService;
	
	private UserService userService;
	private ClusterService clusterService;

	public UserClusterService(UserClusterRepository userClusterRepository, UserService userService , ClusterService clusterService) {
		this.userClusterRepository = userClusterRepository;
		this.userService = userService;
		this.clusterService = clusterService;
	}
	
	
	@Autowired
	public void setRiskFormService(RiskFormService riskFormService) {
		this.riskFormService = riskFormService;
	}
	
	@Autowired
	public void setHearthTestService(HearthTestService hearthTestService) {
		this.hearthTestService = hearthTestService;
	}
	
	@Autowired
	public void setRealAgeService(RealAgeService realAgeService) {
		this.realAgeService = realAgeService;
	}
	
	public UserCluster getOneByUserId(Long userId) {
		return userClusterRepository.findByUserId(userId);
	}
	
	public UserCluster findByUserId(Long userId){
		return userClusterRepository.findByUserId(userId);
	}
	
	public boolean checkUserFillForms(Long userId) {

		boolean hasRiskForm = riskFormService.checkUserExists(userId);
		boolean hasHearthTest = hearthTestService.checkUserExists(userId);
		boolean hasRealAge = realAgeService.checkUserExists(userId);
		
		return hasRiskForm && hasHearthTest && hasRealAge;
	}
	
	public void truncateTable() {
		userClusterRepository.truncateTable();
		//CREATE SEQUENCE package_id_sequence start with 1 increment by 1;
		//userClusterRepository.deleteAll();
	}
	
	
	/*
	public UserCluster saveToUserCluster(User user, Cluster cluster) {
		UserCluster toSave = new UserCluster();
		toSave.setUser(user);
		toSave.setCluster(cluster);
		return userClusterRepository.save(toSave);
	}
	*/
	public UserCluster saveToUserCluster(Long userId, Long clusterId) {
		User user = userService.findById(userId).get();
		Cluster cluster = clusterService.findById(clusterId).get();
		UserCluster toSave = new UserCluster();
		toSave.setUser(user);
		toSave.setCluster(cluster);
		return userClusterRepository.save(toSave);
	}
	
	public UserCluster updateOneUserCluster(Long userId, Long clusterId) {
		UserCluster userCluster = userClusterRepository.findByUserId(userId);
		if (userCluster != null) {
			User user = userService.findById(userId).get();
			Cluster cluster = clusterService.findById(clusterId).get();
			userCluster.setUser(user);
			userCluster.setCluster(cluster);
			return userClusterRepository.save(userCluster);
		}
		return null;
	}

}
