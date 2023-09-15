package com.project.tez.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tez.entities.RealAgeForm;

public interface RealAgeRepository extends JpaRepository<RealAgeForm, Long>{

	boolean existsByUserId(Long userId);
	
	List<RealAgeForm> findByUserId(Long userId);
	
}
