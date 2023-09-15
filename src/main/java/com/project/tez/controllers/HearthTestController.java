package com.project.tez.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tez.entities.HearthTestForm;
import com.project.tez.requests.HearthTestCreateRequest;
import com.project.tez.responses.HearthTestFormResponse;
import com.project.tez.services.HearthTestService;

@RestController
@RequestMapping("/hearthtest")
public class HearthTestController {

	private HearthTestService hearthTestService;
	
	public HearthTestController(HearthTestService hearthTestService) {
		this.hearthTestService = hearthTestService;
	}
	
	@PostMapping
	public HearthTestForm saveToHearthTestForm(@RequestBody HearthTestCreateRequest hearthTestCreateRequest) {
		return hearthTestService.saveToHearthTestForm(hearthTestCreateRequest);
	}
	
	@GetMapping("/calculate_hearth_test")
	public ResponseEntity<HearthTestFormResponse> calculateHearthTest(@RequestParam Long userId){
		return hearthTestService.calculateHearthTest(userId);
	}
	
	
}
