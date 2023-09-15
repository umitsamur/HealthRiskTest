package com.project.tez.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tez.entities.RiskForm;
import com.project.tez.requests.RiskFormCreateRequest;
import com.project.tez.responses.RiskTestFormResponse;
import com.project.tez.services.RiskFormService;

@RestController
@RequestMapping("/")
public class RiskFormController {
	
	private RiskFormService riskFormService;

	public RiskFormController(RiskFormService riskFormService) {
		this.riskFormService = riskFormService;
		
	}
	
	@PostMapping
	public RiskForm saveToRiskForm(@RequestBody RiskFormCreateRequest riskFormCreateRequest) {
		return riskFormService.saveToRiskForm(riskFormCreateRequest);
	}

	@GetMapping("/calculate_risk_test")
	public ResponseEntity<RiskTestFormResponse> calculateRiskTest(@RequestParam Long userId){
		
		return riskFormService.calculateRisk(userId);
	}
}
