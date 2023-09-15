package com.project.tez.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.tez.entities.Cluster;
import com.project.tez.repos.ClusterRepository;

@Service
public class ClusterService {
	
	private ClusterRepository clusterRepository;
	
	public ClusterService(ClusterRepository clusterRepository) {
		this.clusterRepository = clusterRepository;
	}
	
	public Cluster createOneCluster(Cluster cluster) {
		return clusterRepository.save(cluster);
	}
	
	public Optional<Cluster> findById(Long clusterId){
		return clusterRepository.findById(clusterId);
	}

}
