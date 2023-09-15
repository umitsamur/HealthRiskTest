package com.project.tez.services;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.tez.clustering.KMeans;
import com.project.tez.entities.HearthTestForm;
import com.project.tez.entities.User;
import com.project.tez.repos.HearthTestRepository;
import com.project.tez.requests.HearthTestCreateRequest;
import com.project.tez.responses.HearthTestFormResponse;

@Service
public class HearthTestService {
	private static final Logger logger = LoggerFactory.getLogger(HearthTestService.class);
	
	private HearthTestRepository hearthTestRepository;
	private UserService userService;
	private UserClusterService userClusterService;
	
	private KMeans kmeans;
	
	public HearthTestService(HearthTestRepository hearthTestRepository,UserService userService, KMeans kmeans) {
		this.hearthTestRepository = hearthTestRepository;
		this.userService = userService;
		this.kmeans = kmeans;
	}
	
	@Autowired
	public void setUserClusterService(UserClusterService userClusterService) {
		this.userClusterService = userClusterService;
	}

	public HearthTestForm saveToHearthTestForm(HearthTestCreateRequest hearthTestCreateRequest) {
		User user = userService.getOneUserById(hearthTestCreateRequest.getUserId());
		if (user == null) {
			return null;
		}
		HearthTestForm toSave = new HearthTestForm();
		toSave.setUser(user);
		toSave.setHaveGenetic(hearthTestCreateRequest.getHaveGenetic());
		toSave.setAge(hearthTestCreateRequest.getAge());
		toSave.setSmoke(hearthTestCreateRequest.getSmoke());
		toSave.setNutrition(hearthTestCreateRequest.getNutrition());
		toSave.setInsulin(hearthTestCreateRequest.getInsulin());
		toSave.setCholesterol(hearthTestCreateRequest.getCholesterol());
		toSave.setSteatorrhoeicHepatosis(hearthTestCreateRequest.getSteatorrhoeicHepatosis());
		toSave.setInflammation(hearthTestCreateRequest.getInflammation());
		toSave.setRate(hearthTestCreateRequest.getRate());
		toSave.setHypertension(hearthTestCreateRequest.getHypertension());
		toSave.setMovement(hearthTestCreateRequest.getMovement());
		toSave.setStres(hearthTestCreateRequest.getStres());
		hearthTestRepository.save(toSave);
		
		if (userClusterService.checkUserFillForms(hearthTestCreateRequest.getUserId())) {
			kmeans.run();
		}
		
		return toSave;
	}
	
	public ResponseEntity<HearthTestFormResponse> calculateHearthTest(Long userId) {
		
		if (!checkUserExists(userId)) {
			return new ResponseEntity<>(new HearthTestFormResponse(0, ""), HttpStatus.BAD_REQUEST);
		}
		
		logger.info("calculateHearthTest userId: " + userId);
		HearthTestForm form = hearthTestRepository.findByUserId(userId).stream().max(Comparator.comparing(HearthTestForm::getId)).get();
		
		byte haveGenetic =  (byte)form.getHaveGenetic();
		byte age =  (byte)form.getAge();
		byte smoke =  (byte)form.getSmoke();
		byte nutrition =  (byte)form.getNutrition();
		byte insulin =  (byte)form.getInsulin();
		byte cholesterol =  (byte)form.getCholesterol();
		byte steatorrhoeic_hepatosis =  (byte)form.getSteatorrhoeicHepatosis();
		byte inflammation =  (byte)form.getInflammation();
		byte rate =  (byte)form.getRate();
		byte hypertension =  (byte)form.getHypertension();
		byte movement =  (byte)form.getMovement();
		byte stres =  (byte)form.getStres();
		
		int result = 0;
		
		switch(haveGenetic) {
		case 0:
			result += 0;
			break;
		case 1:
			result +=10;
			break;
		case 2:
			result +=20;
			break;
		}
		
		switch(age) {
		case 0:
			result += 0;
			break;
		case 1:
			result += 5;
			break;
		case 2:
			result += 10;
			break;
		}
		
		switch (smoke) {
		case 0:
			result +=0;
			break;
		case 1:
			result +=5;
			break;
		case 2:
			result += 10;
			break;
		case 3:
			result +=20;
			break;
		}
		
		switch(nutrition) {
		case 0:
			result += 10;
			break;
		case 1:
			result +=0;
			break;
		case 2:
			result -=5;
			break;
		}
		
		switch(insulin) {
		case 0:
			result += 0;
			break;
		case 1:
			result +=5;
			break;
		case 2:
			result += 10;
			break;
		case 3:
			result += 20;
			break;
		}
		
		switch(cholesterol) {
		case 0:
			result+=0;
			break;
		case 1:
			result += 10;
			break;
		}
		
		switch(steatorrhoeic_hepatosis) {
		case 0:
			result +=0;
			break;
		case 1:
			result +=10;
			break;
		case 2:
			result+=20;
			break;
		}
		
		switch(inflammation) {
		case 0:
			result+=0;
			break;
		case 1:
			result += 10;
			break;
		}
		
		switch(rate) {
		case 0:
			result +=0;
			break;
		case 1:
			result +=10;
			break;
		case 2:
			result+=20;
			break;
		}
		
		switch(hypertension) {
		case 0:
			result +=0;
			break;
		case 1:
			result +=5;
			break;
		case 2:
			result+=10;
			break;
		}
		
		switch(movement) {
		case 0:
			result +=100;
			break;
		case 1:
			result +=5;
			break;
		case 2:
			result+=0;
			break;
		case 3:
			result -= 10;
			break;
		}
		
		switch(stres) {
		case 0:
			result += 10;
			break;
		case 1:
			result +=0;
			break;
		case 2:
			result -=5;
			break;
		}
		
		
		String message ="";
		if(result < 0) {result = 0;}
		
		if(result >= 0 && result <= 20) {
			message="Sağlıklı durumdasınız";
		}
		else if(result > 20 && result <= 50) {
			message="Dikkat etmelisiniz";
		}
		else if(result > 50)
		{
			message="Risk durumunuz çok yüksek doktora görünmenizde fayda var";
		}
		
		HearthTestFormResponse response = new HearthTestFormResponse(result,message);
		
		return ResponseEntity.ok(response);
	}
	
	public boolean checkUserExists(Long userId) {
		return hearthTestRepository.existsByUserId(userId);
	}

}
