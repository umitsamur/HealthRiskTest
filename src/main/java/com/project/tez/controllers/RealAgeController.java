package com.project.tez.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tez.entities.RealAgeForm;
import com.project.tez.requests.RealAgeCreateRequest;
import com.project.tez.responses.RealAgeFormResponse;
import com.project.tez.services.RealAgeService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/real_age")
public class RealAgeController {
	private static final Logger logger = LoggerFactory.getLogger(RealAgeController.class);
	
	private RealAgeService realAgeService;
	
	public RealAgeController(RealAgeService realAgeService) {
		this.realAgeService = realAgeService;
	}
	
	/*
	@GetMapping
	public int getResult(@RequestParam Long userId) {
		return realAgeService.getResult(userId);
	}*/
	
	@GetMapping("/calculate_real_age")
	public ResponseEntity<RealAgeFormResponse> calculateRealAge(@RequestParam Long userId) {
		return realAgeService.calculateRealAge(userId);
	}
	
	@PostMapping
	public RealAgeForm saveToRealAgeForm(@RequestBody RealAgeCreateRequest realAgeCreateRequest) {
		logger.info("Post: " + realAgeCreateRequest.getUserId());
		return realAgeService.saveToRealAgeForm(realAgeCreateRequest);
	}
	
	private int result = 0;

	@PostMapping("/send_age")
	public Map<String, String> submitForm(@RequestBody Map<String, String> request) {
		
		  for (Map.Entry<String, String> entry : request.entrySet()) { 
		  log.warn("");
		  log.warn(entry.getKey() + ": " + entry.getValue()); 
		  }
		 
		// log.warn(request.get("disease"));
		result = 0;
		String education = request.get("education");
		String disease = request.get("disease");
		String smoke = request.get("smoke");
		String alcohol = request.get("alcohol");
		String movement = request.get("movement");
		String sleep = request.get("sleep");
		String relationship = request.get("relationship");
		String life = request.get("life");
		String mental = request.get("mental");
		String friend = request.get("friend");
		String check_up = request.get("check_up");
		String teeth = request.get("teeth");
		String tension = request.get("tension");
		String bloodsugar = request.get("bloodsugar");
		String cholesterol = request.get("cholesterol");
		String weight = request.get("weight");
		String meat = request.get("meat");
		String fruit = request.get("fruit");
		String water = request.get("water");
		String nuts = request.get("nuts");
		String artificial_nourishment = request.get("artificial_nourishment");

		switch (education) {
		case "0":
			result += 1;
			break;
		case "2":
			result -= 1;
		default:
			result += 0;
		}
		
		
		switch(disease) {
		case "0":
			result += 1;
			break;
		case "1":
			result -=1;
			break;
		}
		
		switch(smoke) {
		case "0":
			result -= 3;
			break;
		case "1":
			result +=1;
			break;
		case "2":
			result +=2;
			break;
		case "3":
			result +=3;
			break;
		}
		
		switch(alcohol) {
		case "0":
			result -= 1;
			break;
		case "1":
			result +=1;
			break;
		case "2":
			result +=2;
			break;
		case "3":
			result +=3;
			break;
		}

		
		switch(movement) {
		case "0":
			result += 2;
			break;
		case "1":
			result +=0;
			break;
		case "2":
			result -=1;
			break;
		case "3":
			result -=2;
			break;
		}
		
		switch(sleep) {
		case "0":
			result += 2;
			break;
		case "1":
			result +=1;
			break;
		case "2":
			result -=1;
			break;
		}
		
		switch(relationship) {
		case "0":
			result += 2;
			break;
		case "1":
			result +=2;
			break;
		case "2":
			result +=1;
			break;
		case "3":
			result -=1;
			break;
		case "4":
			result -=2;
			break;
		}
		
		switch(life) {
		case "0":
			result += 2;
			break;
		case "1":
			result +=1;
			break;
		case "2":
			result -=1;
			break;
		case "3":
			result -=2;
			break;
		}
		
		switch(mental) {
		case "0":
			result -= 1;
			break;
		case "1":
			result +=0;
			break;
		case "2":
			result +=1;
			break;
		case "3":
			result +=2;
			break;
		}
		
		switch(friend) {
		case "0":
			result += 1;
			break;
		case "1":
			result -=1;
			break;
		case "2":
			result -=2;
			break;
		}
		
		switch(check_up) {
		case "0":
			result += 1;
			break;
		case "1":
			result -=1;
			break;
		}
		
		
		switch(teeth) {
		case "0":
			result += 1;
			break;
		case "1":
			result -=1;
			break;
		}
		
		
		switch(tension) {
		case "0":
			result += 2;
			break;
		case "1":
			result -=2;
			break;
		case "2":
			result -=1;
			break;
		case "3":
			result +=2;
			break;
		case "4":
			result +=3;
			break;
		}
		
		switch(bloodsugar) {
		case "0":
			result -= 1;
			break;
		case "1":
			result +=1;
			break;
		case "2":
			result +=3;
			break;
		}
		
		switch(cholesterol) {
		case "0":
			result -= 1;
			break;
		case "1":
			result +=1;
			break;
		case "2":
			result +=2;
			break;
		}
		
		switch(weight) {
		case "0":
			result += 0;
			break;
		case "1":
			result -=1;
			break;
		case "2":
			result +=1;
			break;
		case "3":
			result +=2;
			break;
		case "4":
			result +=3;
			break;
		}
		switch(meat) {
		case "0":
			result -= 1;
			break;
		case "1":
			result +=0;
			break;
		case "2":
			result +=1;
			break;
		case "3":
			result +=2;
			break;
		}
		switch(fruit) {
		case "0":
			result += 2;
			break;
		case "1":
			result +=1;
			break;
		case "2":
			result -=1;
			break;
		case "3":
			result -=2;
			break;
		}
		switch(water) {
		case "0":
			result += 2;
			break;
		case "1":
			result +=1;
			break;
		case "2":
			result -=1;
			break;
		case "3":
			result -=2;
			break;
		}
		
		
		switch(nuts) {
		case "0":
			result += 1;
			break;
		case "1":
			result -=1;
			break;
		case "2":
			result -=2;
			break;
		}
		
		
		switch(artificial_nourishment) {
		case "0":
			result -=2;
			break;
		case "1":
			result +=0;
			break;
		case "2":
			result +=2;
			break;
		}
		
		return request;
	}

	@GetMapping("/sendback_age")
	public Integer sendBackForm() {
		 log.warn(result);
		 return result;
	 }
	
	
}
