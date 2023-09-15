package com.project.tez.controllers;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tez.entities.User;
import com.project.tez.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	//private final static Logger log = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping
	public User saveOneUser(@RequestBody User newUser) {
		newUser.setRole("USER");
		return userService.saveOneUser(newUser);
	}
	
	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Long userId) {
		return userService.getOneUserById(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateOneUser(@PathVariable Long userId, @RequestBody User userToUpdated) {
		return userService.updateOneUser(userId,userToUpdated);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable long userId) {
		userService.deleteOneUser(userId);
	}
	
	
	
	
}
