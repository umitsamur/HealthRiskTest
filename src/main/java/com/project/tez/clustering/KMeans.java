package com.project.tez.clustering;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.tez.entities.UserCluster;
import com.project.tez.repos.RiskFormRepository;
import com.project.tez.services.UserClusterService;

@Component
public class KMeans {
	private RiskFormRepository riskFormRepository;
	
	private UserClusterService userClusterService;
	
	
	public KMeans(RiskFormRepository riskFormRepository) {
		this.riskFormRepository = riskFormRepository;
		
	}
	
	
	@Autowired
	public void setUserClusterService(UserClusterService userClusterService) {
		this.userClusterService = userClusterService;
	}
	
	
	@PostConstruct
	public void init() {
		run();
	}
	
	public void run() {
		List<Object[]> dbDatas = riskFormRepository.getAllDataWithJoins();
		List<DataPoint> dataPoints = new ArrayList<>();
		/*for (Object[] objects : dbDatas) {
			for (Object object : objects) {
				System.out.print(object + " " );
			}
			System.out.println(objects[0]);
		}
		
		System.out.println("Db size: " + dbDatas.size());*/
		
		for (int i = 0; i < dbDatas.size(); i++) {
			Object[] dbData = dbDatas.get(i);
			
			dataPoints.add(new DataPoint(((BigDecimal)dbData[0]).longValue() , Double.parseDouble(String.valueOf(dbData[1])), Double.parseDouble(String.valueOf(dbData[2])), 
					Double.parseDouble(String.valueOf(dbData[3])),
					Double.parseDouble(String.valueOf(dbData[4])), Double.parseDouble(String.valueOf(dbData[5])), Double.parseDouble(String.valueOf(dbData[6])), 
					Double.parseDouble(String.valueOf(dbData[7])), Double.parseDouble(String.valueOf(dbData[8])), 			  Double.parseDouble(String.valueOf(dbData[9])), 
					Double.parseDouble(String.valueOf(dbData[10])), Double.parseDouble(String.valueOf(dbData[11])), Double.parseDouble(String.valueOf(dbData[12])),
					Double.parseDouble(String.valueOf(dbData[13])), Double.parseDouble(String.valueOf(dbData[14])), Double.parseDouble(String.valueOf(dbData[15])), 
					Double.parseDouble(String.valueOf(dbData[16])), Double.parseDouble(String.valueOf(dbData[17])), Double.parseDouble(String.valueOf(dbData[18])), 
					Double.parseDouble(String.valueOf(dbData[19])), Double.parseDouble(String.valueOf(dbData[20])), 		  Double.parseDouble(String.valueOf(dbData[21])), 
					Double.parseDouble(String.valueOf(dbData[22])), Double.parseDouble(String.valueOf(dbData[23])), Double.parseDouble(String.valueOf(dbData[24])), 
					Double.parseDouble(String.valueOf(dbData[25])), Double.parseDouble(String.valueOf(dbData[26])), Double.parseDouble(String.valueOf(dbData[27])), 
					Double.parseDouble(String.valueOf(dbData[28])), Double.parseDouble(String.valueOf(dbData[29])), Double.parseDouble(String.valueOf(dbData[30])),
					Double.parseDouble(String.valueOf(dbData[31])), Double.parseDouble(String.valueOf(dbData[32])), Double.parseDouble(String.valueOf(dbData[33])), 
					Double.parseDouble(String.valueOf(dbData[34])), Double.parseDouble(String.valueOf(dbData[35])), Double.parseDouble(String.valueOf(dbData[36])), 
					Double.parseDouble(String.valueOf(dbData[37])), Double.parseDouble(String.valueOf(dbData[38])), Double.parseDouble(String.valueOf(dbData[39])), 
					Double.parseDouble(String.valueOf(dbData[40])), Double.parseDouble(String.valueOf(dbData[41])), Double.parseDouble(String.valueOf(dbData[42])), 
					Double.parseDouble(String.valueOf(dbData[43])), Double.parseDouble(String.valueOf(dbData[44])), Double.parseDouble(String.valueOf(dbData[45]))));
			
			
		}
		
		
		//System.out.println("------------CHECK--------------");
		/*for (List<DataPoint> list : dataPoints) {
			for (DataPoint dp : list) {
				System.out.println("UserId: " + dp.getUserId() + " Alcohol: " + dp.getAlcohol() + " animalfats: " + dp.getAnimalfats());
			}
		}*/
		/*for (DataPoint dp : dataPoints) {
			System.out.println("UserId: " + dp.getUserId() + " Alcohol: " + dp.getAlcohol() + " animalfats: " + dp.getAnimalfats());
		}*/
		// K-Means algoritması için gerekli parametreler
		int k = 4;
		int maxIterations = 100;
		List<DataPoint> centroids = runKMeans(dataPoints, k, maxIterations);
		
		System.out.println("Cluster Merkezleri: ");
		for (int i = 0; i < centroids.size(); i++) {
			System.out.println("Cluster " + (i + 1) + ": " + centroids.get(i));
		}

		System.out.println("\nVeri noktaları ve atandığı Cluster");
		for (DataPoint point : dataPoints) {
			System.out.println(point.getUserId() + " -> Cluster " + (point.getCluster() + 1));
		}
		
		for (DataPoint point : dataPoints) {
			//önemli alttaki 3 satırı silme ilk önce kontrol et
			//User user = userService.findById(point.getUserId()).get();
			//Cluster cluster = clusterService.findById((long)(point.getCluster() + 1)).get();
			//System.out.println(user.getId() + " -> Cluster " + cluster.getId());
			
			
			//userClusterService.saveToUserCluster(user, cluster);
			
			UserCluster userCluster = userClusterService.findByUserId(point.getUserId());
			if (userCluster != null) {
				userCluster = userClusterService.updateOneUserCluster(point.getUserId(), ((long)(point.getCluster() + 1)));
			}
			else {
				userCluster = userClusterService.saveToUserCluster(point.getUserId(), ((long)(point.getCluster() + 1)));
			}
			
			//System.out.println(point.getUserId() + " -> Cluster " + (point.getCluster() + 1));
			
		}
	}
	
	public static List<DataPoint> runKMeans(List<DataPoint> points, int k, int maxIterations) {
		// Veri noktalarının rastgele seçilen k centroid'e atanmasıyla başla
		List<DataPoint> centroids = initializeCentroids(points, k);

		for (int iteration = 0; iteration < maxIterations; iteration++) {
			// Her bir veri noktasını en yakın centroid'e atama
			assignPointsToClusters(points, centroids);

			// Cluster merkezlerini güncelleme
			List<DataPoint> newCentroids = calculateCentroids(points, k);

			// Eğer cluster merkezleri değişmediyse algoritmayı sonlandır	
			if(centroids.equals(newCentroids)) {
				break;
			}

			centroids = newCentroids;
		}

		return centroids;
	}
	
	private static List<DataPoint> initializeCentroids(List<DataPoint> points, int k) {
		Random random = new Random();
		List<DataPoint> centroids = new ArrayList<>();
		
		ArrayList<Integer> randomValues = new ArrayList<Integer>();
		
		for (int i = 0; i < k; i++) {
			int randomValue = random.nextInt(points.size());
			
			while(randomValues.contains(randomValue)) {
				randomValue = random.nextInt(points.size());
			}
			DataPoint centroid = points.get(randomValue);
			centroid.setCluster(i);
			centroids.add(new DataPoint(centroid.getUserId(), centroid.getAlcohol(), centroid.getAnimalfats(), centroid.getArtificialsweetener(), centroid.getCharcuterie(),
					centroid.getFiberfood(), centroid.getFizzydrink(), centroid.getHave_cancer(), centroid.getHeight(), centroid.getHepatit(), centroid.getHpv(),
					centroid.getInsufficient(), centroid.getJob(), centroid.getMovement(), centroid.getProcessedfood(), centroid.getPylori(), centroid.getRedmeat(),
					centroid.getSmoke(), centroid.getStres(), centroid.getVirus(), centroid.getWeight(),
					centroid.getArtificial_nourishment(), centroid.getBloodsugar(), centroid.getCheck_up(), centroid.getCholesterol(), centroid.getDisease(),
					centroid.getEducation(), centroid.getFriend(), centroid.getFruit(), centroid.getLife(), centroid.getMeat(), centroid.getMental(), centroid.getNuts(),
					centroid.getRelationship(), centroid.getSleep(), centroid.getTeeth(), centroid.getTension(), centroid.getWater(),
					centroid.getAge(), centroid.getHave_genetic(), centroid.getHypertension(), centroid.getInflammation(), centroid.getInsulin(), centroid.getNutrition(),
					centroid.getRate(), centroid.getSteatorrhoeic_hepatosis()));
			randomValues.add(randomValue);
		}

		return centroids;
	}
	
	
	private static void assignPointsToClusters(List<DataPoint> points, List<DataPoint> centroids) {
		for (DataPoint point : points) {
			double minDistance = Double.MAX_VALUE;
			int assignedCluster = -1;

			for (int i = 0; i < centroids.size(); i++) {
				DataPoint centroid = centroids.get(i);
				double distance = calculateEuclideanDistance(point, centroid);

				if (distance < minDistance) {
					minDistance = distance;
					assignedCluster = i;
				}
			}

			point.setCluster(assignedCluster);
		}
	}
	
	private static List<DataPoint> calculateCentroids(List<DataPoint> points, int k) {
		List<DataPoint> centroids = new ArrayList<>();

		for (int i = 0; i < k; i++) {
			
			double sumAlcohol = 0;		double sumAnimalfats = 0;		double sumArtificialsweetener = 0;		double sumCharcuterie = 0;
			double sumFiberfood = 0;	double sumFizzydrink = 0;		double sumHave_cancer = 0;				double sumHeight = 0;
			double sumHepatit = 0;		double sumHpv = 0;				double sumInsufficient = 0;				double sumJob = 0;
			double sumMovement = 0;		double sumProcessedFood = 0;	double sumPylori = 0;					double sumRedMeat = 0;
			double sumSmoke = 0;		double sumStres = 0;			double sumVirus = 0;					double sumWeight = 0;
			double sumArtificial_nourishment = 0;	double sumBloodsugar = 0;	double sumCheck_up = 0;			double sumCholesterol = 0;
			double sumDisease = 0;		double sumEducation = 0;		double sumFriend = 0;					double sumFruit = 0;
			double sumLife = 0;			double sumMeat = 0;				double sumMental = 0;					double sumNuts = 0;
			double sumRelationship = 0;	double sumSleep = 0;			double sumTeeth = 0;					double sumTension = 0;
			double sumWater = 0;		double sumAge = 0;				double sumHave_genetic = 0;				double sumHypertension = 0;
			double sumInflammation = 0;	double sumInsulin = 0;			double sumNutrition = 0;				double sumRate = 0;
			double sumSteatorrhoeic_hepatosis = 0;
			
			int count = 0;

			for (DataPoint point : points) {
				if (point.getCluster() == i) {
					sumAlcohol += point.getAlcohol();	sumAnimalfats += point.getAnimalfats();		sumArtificialsweetener += point.getArtificialsweetener();
					sumCharcuterie += point.getCharcuterie();	sumFiberfood += point.getFiberfood();	sumFizzydrink += point.getFizzydrink();
					sumHave_cancer += point.getHave_cancer();	sumHeight += point.getHeight();		sumHepatit += point.getHepatit();
					sumHpv += point.getHpv();			sumInsufficient += point.getInsufficient();	sumJob += point.getJob();
					sumMovement += point.getMovement();	sumProcessedFood += point.getProcessedfood();	sumPylori += point.getPylori();
					sumRedMeat += point.getRedmeat();	sumSmoke += point.getSmoke();	sumStres += point.getStres();
					sumVirus += point.getVirus();		sumWeight += point.getWeight();	sumArtificial_nourishment += point.getArtificial_nourishment();
					sumBloodsugar += point.getBloodsugar();	sumCheck_up += point.getCheck_up();		sumCholesterol += point.getCholesterol();
					sumDisease += point.getDisease();	sumEducation += point.getEducation();	sumFriend += point.getFriend(); sumFruit += point.getFruit();
					sumLife += point.getLife();	sumMeat += point.getMeat(); sumMental += point.getMental(); sumNuts += point.getNuts();
					sumRelationship += point.getRelationship(); sumSleep += point.getSleep(); sumTeeth += point.getTeeth(); sumTension += point.getTension();
					sumWater += point.getWater(); sumAge += point.getAge();  sumHave_genetic += point.getHave_genetic(); sumHypertension += point.getHypertension();
					sumInflammation += point.getInflammation(); sumInsulin += point.getInsulin(); sumNutrition += point.getNutrition(); sumRate += point.getRate();
					sumSteatorrhoeic_hepatosis += point.getSteatorrhoeic_hepatosis();
					
					count++;
				}
			}

			if (count > 0) {
				DataPoint centroidValues = new DataPoint();
				centroidValues.setAlcohol(sumAlcohol / count);
				centroidValues.setAnimalfats(sumAnimalfats / count);
				centroidValues.setArtificialsweetener(sumArtificialsweetener / count);
				centroidValues.setCharcuterie(sumCharcuterie / count);
				centroidValues.setFiberfood(sumFiberfood / count);
				centroidValues.setFizzydrink(sumFizzydrink / count);
				centroidValues.setHave_cancer(sumHave_cancer / count);
				centroidValues.setHeight(sumHeight / count);
				centroidValues.setHepatit(sumHepatit / count);
				centroidValues.setHpv(sumHpv / count);
				centroidValues.setInsufficient(sumInsufficient / count);
				centroidValues.setJob(sumJob / count);
				centroidValues.setMovement(sumMovement / count);
				centroidValues.setProcessedfood(sumProcessedFood / count);
				centroidValues.setPylori(sumPylori / count);
				centroidValues.setRedmeat(sumRedMeat / count);
				centroidValues.setSmoke(sumSmoke / count);
				centroidValues.setStres(sumStres / count);
				centroidValues.setVirus(sumVirus / count);
				centroidValues.setWeight(sumWeight / count);
				centroidValues.setArtificial_nourishment(sumArtificial_nourishment / count);
				centroidValues.setBloodsugar(sumBloodsugar / count);
				centroidValues.setCheck_up(sumCheck_up / count);
				centroidValues.setCholesterol(sumCholesterol / count);
				centroidValues.setDisease(sumDisease / count);
				centroidValues.setEducation(sumEducation / count);
				centroidValues.setFriend(sumFriend / count);
				centroidValues.setFruit(sumFruit / count);
				centroidValues.setLife(sumLife / count);
				centroidValues.setMeat(sumMeat / count);
				centroidValues.setMental(sumMental / count);
				centroidValues.setNuts(sumNuts / count);
				centroidValues.setRelationship(sumRelationship / count);
				centroidValues.setSleep(sumSleep / count);
				centroidValues.setTeeth(sumTeeth / count);
				centroidValues.setTension(sumTension / count);
				centroidValues.setWater(sumWater / count);
				centroidValues.setAge(sumAge / count);
				centroidValues.setHave_genetic(sumHave_genetic / count);
				centroidValues.setHypertension(sumHypertension / count);
				centroidValues.setInflammation(sumInflammation / count);
				centroidValues.setInsulin(sumInsulin / count);
				centroidValues.setNutrition(sumNutrition / count);
				centroidValues.setRate(sumRate / count);
				centroidValues.setSteatorrhoeic_hepatosis(sumSteatorrhoeic_hepatosis / count);
				centroids.add(centroidValues);
			}
		}
		return centroids;
	}
	
	private static double calculateEuclideanDistance(DataPoint p1, DataPoint p2) {
		
		double dAlcohol = p1.getAlcohol() - p2.getAlcohol();
		double dAnimalfats = p1.getAnimalfats() - p2.getAnimalfats();
		double dAs = p1.getArtificialsweetener() - p2.getArtificialsweetener();
		double dCharturie = p1.getCharcuterie() - p2.getCharcuterie();
		double dFiberf = p1.getFiberfood() - p2.getFiberfood();
		
		double dFdrink = p1.getFizzydrink() - p2.getFizzydrink();
		double dCancer = p1.getHave_cancer() - p2.getHave_cancer();
		double dHeight = p1.getHeight() - p2.getHeight();
		double dHepatit = p1.getHepatit() - p2.getHepatit();
		double dHpv = p1.getHpv() - p2.getHpv();
		
		double dInsuf = p1.getInsufficient() - p2.getInsufficient();
		double dJob = p1.getJob() - p2.getJob();
		double dMov = p1.getMovement() - p2.getMovement();
		double dProcFood = p1.getProcessedfood() - p2.getProcessedfood();
		double dPylori = p1.getPylori() - p2.getPylori();
		
		double dRedMeat = p1.getRedmeat() - p2.getRedmeat();
		double dSmoke = p1.getSmoke() - p2.getSmoke();
		double dStres = p1.getStres() - p2.getStres();
		double dVirus = p1.getVirus() - p2.getVirus();
		double dWeight = p1.getWeight() - p2.getWeight();
		
		double dArtiNo = p1.getArtificial_nourishment() - p2.getArtificial_nourishment();
		double dBSugar = p1.getBloodsugar() - p2.getBloodsugar();
		double dCUp = p1.getCheck_up() - p2.getCheck_up();
		double dChol = p1.getCholesterol() - p2.getCholesterol();
		double dDisease = p1.getDisease() - p2.getDisease();
		
		double dEducation = p1.getEducation() - p2.getEducation();
		double dFriend = p1.getFriend() - p2.getFriend();
		double dFruit = p1.getFruit() - p2.getFruit();
		double dLife = p1.getLife() - p2.getLife();
		double dMeat = p1.getMeat() - p2.getMeat();
		
		double dMental = p1.getMental() - p2.getMental();
		double dNuts = p1.getNuts() - p2.getNuts();
		double dRShip = p1.getRelationship() - p2.getRelationship();
		double dSleep = p1.getSleep() - p2.getSleep();
		double dTeeth = p1.getTeeth() - p2.getTeeth();
		
		double dTension = p1.getTension() - p2.getTension();
		double dWater = p1.getWater() - p2.getWater();
		double dAge = p1.getAge() - p2.getAge();
		double dHGenetic = p1.getHave_genetic() - p2.getHave_genetic();
		double dHypertension = p1.getHypertension() - p2.getHypertension();
		
		double dInflammation = p1.getInflammation() - p2.getInflammation();
		double dInsulin = p1.getInsulin() - p2.getInsulin();
		double dNutrition = p1.getNutrition() - p2.getNutrition();
		double dRate = p1.getRate() - p2.getRate();
		double dSHepatosis = p1.getSteatorrhoeic_hepatosis() - p2.getSteatorrhoeic_hepatosis();
		
		return Math.sqrt(dAlcohol * dAlcohol + dAnimalfats * dAnimalfats + dAs * dAs + dCharturie * dCharturie + dFiberf * dFiberf +
							dFdrink * dFdrink +  dCancer * dCancer + dHeight * dHeight + dHepatit * dHepatit + dHpv * dHpv + 
							dInsuf * dInsuf +  dJob * dJob + dMov * dMov + dProcFood * dProcFood + dPylori * dPylori + 
							dRedMeat * dRedMeat +  dSmoke * dSmoke + dStres * dStres + dVirus * dVirus + dWeight * dWeight + 
							dArtiNo * dArtiNo +  dBSugar * dBSugar + dCUp * dCUp + dChol * dChol + dDisease * dDisease + 
							dEducation * dEducation +  dFriend * dFriend + dFruit * dFruit + dLife * dLife + dMeat * dMeat + 
							dMental * dMental +  dNuts * dNuts + dRShip * dRShip + dSleep * dSleep + dTeeth * dTeeth + 
							dTension * dTension +  dWater * dWater + dAge * dAge + dHGenetic * dHGenetic + dHypertension * dHypertension + 
							dInflammation * dInflammation +  dInsulin * dInsulin + dNutrition * dNutrition + dRate * dRate + dSHepatosis * dSHepatosis);
	}
	

}
