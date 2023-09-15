package com.project.tez.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tez.entities.User;
import com.project.tez.requests.UserRequest;
import com.project.tez.responses.AuthResponse;
import com.project.tez.security.JwtTokenProvider;
import com.project.tez.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private UserService userService;
	private PasswordEncoder passwordEncoder;
	
	public AuthController(AuthenticationManager authenticationManager,JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		log.info("Authtoken: " + authToken.toString());
		Authentication auth = authenticationManager.authenticate(authToken);
		log.info("auth: " + auth.toString());
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		log.info("jwtToken: " + jwtToken.toString());
		
		User user = userService.getOneUserByEmail(loginRequest.getEmail());
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setMessage("Bearer " + jwtToken);
		log.info("Bearer " + jwtToken);
		authResponse.setAccessToken("Bearer " + jwtToken);
		log.info("userid: " + user.getId());
		authResponse.setUserId(user.getId());
		
		//statusOK(jwtToken);
		HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
	    headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");
		
		return ResponseEntity.ok().headers(headers)
									.body(authResponse);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest){
		AuthResponse authResponse = new AuthResponse();
		if (userService.getOneUserByEmail(registerRequest.getEmail()) != null) {
			authResponse.setMessage("Email already in use.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setName(registerRequest.getName());
		user.setSurname(registerRequest.getSurname());
		user.setGender(registerRequest.getGender());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setOccupation(registerRequest.getOccupation());
		user.setContactNumber(registerRequest.getContactNumber());
		user.setBirthDate(registerRequest.getBirthDate());
		
		
		userService.saveOneUser(user);
		authResponse.setMessage("User successfully registered.");
		
		//
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		
		authResponse.setMessage("User successfully registered.");
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setUserId(user.getId());
		//
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}
	/*
	public ResponseEntity<?> statusOK(String jwts){
		
		return ResponseEntity.ok()
								.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
								.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
								.build();
	}
	*/
}
