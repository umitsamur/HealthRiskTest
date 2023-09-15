package com.project.tez.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.tez.entities.User;
import com.project.tez.repos.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	public User updateOneUser(Long userId, User userToUpdated) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User existingUser = user.get();
			existingUser.setName(userToUpdated.getName());
			existingUser.setSurname(userToUpdated.getSurname());
			existingUser.setPassword(userToUpdated.getPassword());
			existingUser.setEmail(userToUpdated.getEmail());
			existingUser.setContactNumber(userToUpdated.getContactNumber());
			existingUser.setBirthDate(userToUpdated.getBirthDate());
			existingUser.setGender(userToUpdated.getGender());
			existingUser.setOccupation(userToUpdated.getOccupation());
			userRepository.save(existingUser);
			return existingUser;
		}
		return null;
	}

	public void deleteOneUser(long userId) {
		userRepository.deleteById(userId);
	}
	
	
	public User getOneUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Date getAgeOneUserById(Long userId) {
		return userRepository.findBirthDateById(userId);
	}
}
