package com.project.tez.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tez.entities.Cluster;
import com.project.tez.services.ClusterService;

@RestController
@RequestMapping("/cluster")
public class ClusterController {
	
	private ClusterService clusterService;
	
	public ClusterController(ClusterService clusterService) {
		this.clusterService = clusterService;
	}

	@PostMapping
	public Cluster createOneCluster(@RequestBody Cluster cluster) {
		return clusterService.createOneCluster(cluster);
	}
	
}
