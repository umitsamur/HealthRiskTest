package com.project.tez.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.tez.clustering.KMeans;
import com.project.tez.entities.RealAgeForm;
import com.project.tez.entities.User;
import com.project.tez.repos.RealAgeRepository;
import com.project.tez.requests.RealAgeCreateRequest;
import com.project.tez.responses.RealAgeFormResponse;

@Service
public class RealAgeService {
	private static final Logger logger = LoggerFactory.getLogger(RealAgeService.class);
	
	private RealAgeRepository realAgeRepository;
	private UserService userService;
	private UserClusterService userClusterService;
	
	private KMeans kmeans;
	
	
	public RealAgeService(RealAgeRepository realAgeRepository, UserService userService, KMeans kmeans) {
		this.realAgeRepository = realAgeRepository;
		this.userService = userService;
		this.kmeans = kmeans;
	}
	
	@Autowired
	public void setUserClusterService(UserClusterService userClusterService) {
		this.userClusterService = userClusterService;
	}

	public List<RealAgeForm> getAllRealAge() {
		return realAgeRepository.findAll();
	}

	public RealAgeForm saveToRealAgeForm(RealAgeCreateRequest realAgeCreateRequest) {
		User user = userService.getOneUserById(realAgeCreateRequest.getUserId());
		if(user == null) {
			return null;
		}
		RealAgeForm toSave = new RealAgeForm();
		toSave.setUser(user);
		toSave.setEducation(realAgeCreateRequest.getEducation());
		toSave.setDisease(realAgeCreateRequest.getDisease());
		toSave.setSmoke(realAgeCreateRequest.getSmoke());
		toSave.setAlcohol(realAgeCreateRequest.getAlcohol());
		toSave.setMovement(realAgeCreateRequest.getMovement());
		toSave.setSleep(realAgeCreateRequest.getSleep());
		toSave.setRelationship(realAgeCreateRequest.getRelationship());
		toSave.setLife(realAgeCreateRequest.getLife());
		toSave.setMental(realAgeCreateRequest.getMental());
		toSave.setFriend(realAgeCreateRequest.getFriend());
		toSave.setCheck_up(realAgeCreateRequest.getCheck_up());
		toSave.setTeeth(realAgeCreateRequest.getTeeth());
		toSave.setTension(realAgeCreateRequest.getTension());
		toSave.setBloodsugar(realAgeCreateRequest.getBloodsugar());
		toSave.setCholesterol(realAgeCreateRequest.getCholesterol());
		toSave.setWeight(realAgeCreateRequest.getWeight());
		toSave.setMeat(realAgeCreateRequest.getMeat());
		toSave.setFruit(realAgeCreateRequest.getFruit());
		toSave.setWater(realAgeCreateRequest.getWater());
		toSave.setNuts(realAgeCreateRequest.getNuts());
		toSave.setArtificial_nourishment(realAgeCreateRequest.getArtificial_nourishment());
		
		logger.info("toSave Öncesi");
		realAgeRepository.save(toSave);
		logger.info("toSave Sonrası");
		logger.info("return Öncesi");
		if(userClusterService.checkUserFillForms(realAgeCreateRequest.getUserId())) {
			kmeans.run();
		}
		
		
		return toSave;
	}
	
	public ResponseEntity<RealAgeFormResponse> calculateRealAge(Long userId) {
		
		if (!checkUserExists(userId)) {
			return new ResponseEntity<>(new RealAgeFormResponse(0,0,""), HttpStatus.BAD_REQUEST);
		}
		
		logger.info("{service} userid: " + userId);
		LocalDate birthDate = userService.getAgeOneUserById(userId).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		logger.info(birthDate.toString());
		LocalDate currentDate = LocalDate.now();
		int age = Period.between(birthDate, currentDate).getYears();
		logger.info("Age: " +age);
		
		RealAgeForm form = realAgeRepository.findByUserId(userId).stream().max(Comparator.comparing(RealAgeForm::getId)).get();
		int result = 0;
		
		String education = String.valueOf(form.getEducation());
		String disease = String.valueOf(form.getDisease());
		String smoke = String.valueOf(form.getSmoke());
		String alcohol = String.valueOf(form.getAlcohol());
		String movement = String.valueOf(form.getMovement());
		String sleep = String.valueOf(form.getSleep());
		String relationship = String.valueOf(form.getRelationship());
		String life = String.valueOf(form.getLife());
		String mental = String.valueOf(form.getMental());
		String friend = String.valueOf(form.getFriend());
		String check_up = String.valueOf(form.getCheck_up());
		String teeth = String.valueOf(form.getTeeth());
		String tension = String.valueOf(form.getTension());
		String bloodsugar = String.valueOf(form.getBloodsugar());
		String cholesterol = String.valueOf(form.getCholesterol());
		String weight = String.valueOf(form.getWeight());
		String meat = String.valueOf(form.getMeat());
		String fruit = String.valueOf(form.getFruit());
		String water = String.valueOf(form.getWater());
		String nuts = String.valueOf(form.getNuts());
		String artificial_nourishment = String.valueOf(form.getArtificial_nourishment());

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
		
		int realAge = age + result;
		
		
		String message = realAge < age ? "Sağlıklı" : "Dikkat etmelisiniz ! ";
		logger.info("Real Age: "+ realAge+ " message: " + message);
		RealAgeFormResponse realAgeFormResponse = new RealAgeFormResponse(age,realAge,message);
		return ResponseEntity.ok(realAgeFormResponse);
		
	}
	
	public boolean checkUserExists(Long userId) {
		return realAgeRepository.existsByUserId(userId);
	}

}
