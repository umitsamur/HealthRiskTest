package com.project.tez.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.tez.entities.Forum;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

	Optional<Forum> findById(Long forumId);
	
	List<Forum> findByUserId(Long userId);
	
}
