package com.project.tez.repos;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.tez.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	@Query(value="select BIRTH_DATE from users where id= :userId",nativeQuery = true)
	Date findBirthDateById(@Param("userId") Long userId);

}
