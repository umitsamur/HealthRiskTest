package com.project.tez.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tez.entities.HearthTestForm;

public interface HearthTestRepository extends JpaRepository<HearthTestForm, Long> {

	boolean existsByUserId(Long userId);
	
	List<HearthTestForm> findByUserId(Long userId);
	
}
