package com.project.tez.services;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.tez.clustering.KMeans;
import com.project.tez.entities.RiskForm;
import com.project.tez.entities.User;
import com.project.tez.repos.RiskFormRepository;
import com.project.tez.requests.RiskFormCreateRequest;
import com.project.tez.responses.RiskTestFormResponse;

@Service
public class RiskFormService {
	private static final Logger logger = LoggerFactory.getLogger(RiskFormService.class);
	
	private RiskFormRepository riskFormRepository;
	private UserService userService;
	private UserClusterService userClusterService;
	
	private KMeans kmeans;
	
	public RiskFormService(RiskFormRepository riskFormRepository, UserService userService, KMeans kmeans) {
		this.riskFormRepository = riskFormRepository;
		this.userService = userService;
		this.kmeans = kmeans;
	}
	
	@Autowired
	public void setUserClusterService(UserClusterService userClusterService) {
		this.userClusterService = userClusterService;
	}
	

	public RiskForm saveToRiskForm(RiskFormCreateRequest riskFormCreateRequest) {
		User user = userService.getOneUserById(riskFormCreateRequest.getUserId());
		if(user == null) {
			return null;
		}
		RiskForm toSave = new RiskForm();
		toSave.setUser(user);
		toSave.setHaveCancer(riskFormCreateRequest.getHaveCancer());
		toSave.setSmoke(riskFormCreateRequest.getSmoke());
		
		toSave.setRedmeat(riskFormCreateRequest.isRedmeat() ? 1 : 0);
		toSave.setCharcuterie(riskFormCreateRequest.isCharcuterie( )? 1 : 0);
		toSave.setAnimalfats(riskFormCreateRequest.isAnimalfats() ? 1 : 0);
		toSave.setFiberfood(riskFormCreateRequest.isFiberfood() ? 1 : 0);
		toSave.setInsufficient(riskFormCreateRequest.isInsufficient() ? 1 : 0);
		toSave.setProcessedfood(riskFormCreateRequest.isProcessedfood() ? 1 : 0);
		toSave.setFizzydrink(riskFormCreateRequest.isFizzydrink() ? 1 : 0);
		toSave.setArtificialsweetener(riskFormCreateRequest.isArtificialsweetener() ? 1 : 0);
		
		toSave.setWeight(riskFormCreateRequest.getWeight());
		toSave.setHeight(riskFormCreateRequest.getHeight());
		
		toSave.setHepatit(riskFormCreateRequest.isHepatit() ? 1 : 0);
		toSave.setVirus(riskFormCreateRequest.isVirus() ? 1 : 0);
		toSave.setHpv(riskFormCreateRequest.isHpv() ? 1 : 0);
		toSave.setPylori(riskFormCreateRequest.isPylori() ? 1 : 0);
		
		
		toSave.setAlcohol(riskFormCreateRequest.getAlcohol());
		toSave.setStres(riskFormCreateRequest.getStres());
		toSave.setJob(riskFormCreateRequest.getJob());
		toSave.setMovement(riskFormCreateRequest.getMovement());
		
		riskFormRepository.save(toSave);
		
		logger.info("if öncesi");
		if (userClusterService.checkUserFillForms(riskFormCreateRequest.getUserId())) {
			logger.info("kmeans öncesi");
			kmeans.run();
			logger.info("kmeans sonrası");
		}
		
		return toSave;
	}

	public ResponseEntity<RiskTestFormResponse> calculateRisk(Long userId) {
		
		if (!checkUserExists(userId)) {
			return new ResponseEntity<>(new RiskTestFormResponse(0, ""), HttpStatus.BAD_REQUEST);
		}
		
		RiskForm form = riskFormRepository.findByUserId(userId).stream().max(Comparator.comparing(RiskForm::getId)).get();
		
		short result = 0;
		
		result += (short) ((byte)form.getHaveCancer() == 0 ? 0 : 7);
		result += (short) ((byte)form.getSmoke() == 0 ? 0 : 15);
		result += (short) ((byte)form.getRedmeat() == 0 ? 0 : 3);
		result += (short) ((byte)form.getCharcuterie() == 0 ? 0 : 3);
		result += (short) ((byte)form.getAnimalfats() == 0 ? 0 : 3);
		result += (short) ((byte)form.getFiberfood() == 0 ? 0 : 3);
		result += (short) ((byte)form.getInsufficient() == 0 ? 0 : 3);
		result += (short) ((byte)form.getProcessedfood() == 0 ? 0 : 3);
		result += (short) ((byte)form.getFizzydrink() == 0 ? 0 : 3);
		result += (short) ((byte)form.getArtificialsweetener() == 0 ? 0 : 3);
		result += calculateBMI((short)form.getWeight(), (short)form.getHeight());
		result += (short) ((byte)form.getHepatit() == 0 ? 0 : 5);
		result += (short) ((byte)form.getHpv() == 0 ? 0 : 5);
		result += (short) ((byte)form.getVirus() == 0 ? 0 : 5);
		result += (short) ((byte)form.getPylori() == 0 ? 0 : 5);
		
		switch((byte)form.getAlcohol()) {
		case 0:
			result+=0;
			break;
		case 1:
			result+=5;
			break;
		case 2:
			result+=10;
			break;
		case 3:
			result+=20;
			break;
		}
		
		result += (short) ((byte)form.getJob() == 0 ? 0 : 13);
		result += (short) ((byte)form.getStres() == 0 ? 0 : 5);
		result += (short) ((byte)form.getMovement() == 0 ? 5 : 0);
		
		
		String message = "";
		
		if(result < 50) {
			message = "Sağlıklı";
		}
		else if(result >= 50 && result < 80) {
			message = "Yaşam tarzınıza dikkat etmelisiniz !";
		}
		else if(result >= 80) {
			message = "Çok acil uzmanlara danışmalısınız";
		}
		
		RiskTestFormResponse response = new RiskTestFormResponse(result, message);
		
		
		return ResponseEntity.ok(response);
	}
	

	private short calculateBMI(short weight, short height) {
		double dWeight = (double) weight;
		double dHeight = ((double) height)/100;
		double bmi = dWeight/(dHeight*dHeight);
		logger.info("Weight: "+ dWeight + "\nHeight: " + dHeight + "\nbmi: " + bmi);
		if(bmi > 0 && bmi <25) {
			return 0;
		}
		else if (bmi >= 25 && bmi < 30) {
			return 5;
		}
		else {
			return 10;
		}
	}

	public boolean checkUserExists(Long userId) {
		return riskFormRepository.existsByUserId(userId);
	}
	
	
}
