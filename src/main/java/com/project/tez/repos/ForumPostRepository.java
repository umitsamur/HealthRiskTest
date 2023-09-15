package com.project.tez.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.tez.entities.ForumPost;

public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {
	
	List<ForumPost> findByUserClusterIdIn(List<Long> ids);
	
	@Query(value = "select id from user_cluster where cluster_id = :clusterId" ,nativeQuery = true)
	List<Long> getIdsByClusterId(@Param("clusterId") Long clusterId);

	@Query(value = "DROP TABLE forum_post", nativeQuery = true)
	void dropTable();
}
