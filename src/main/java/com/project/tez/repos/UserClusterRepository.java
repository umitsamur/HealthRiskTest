package com.project.tez.repos;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.tez.entities.UserCluster;

public interface UserClusterRepository extends JpaRepository<UserCluster, Long> {
	
	UserCluster findByUserId(Long userId);
	
	@Transactional
	@Query(value = "TRUNCATE TABLE user_cluster", nativeQuery = true)
    public void truncateTable();
	
	
}
