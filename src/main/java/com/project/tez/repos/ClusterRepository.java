package com.project.tez.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tez.entities.Cluster;

public interface ClusterRepository extends JpaRepository<Cluster, Long> {

}
