package com.project.tez.clustering;


public class DataPoint {

	private Long userId;

	// risk form
	private double alcohol;
	private double animalfats;
	private double artificialsweetener;
	private double charcuterie;
	private double fiberfood;
	private double fizzydrink;
	private double have_cancer;
	private double height;
	private double hepatit;
	private double hpv;
	private double insufficient;
	private double job;
	private double movement;
	private double processedfood;
	private double pylori;
	private double redmeat;
	private double smoke;
	private double stres;
	private double virus;
	private double weight;

	// real age
	private double artificial_nourishment;
	private double bloodsugar;
	private double check_up;
	private double cholesterol;
	private double disease;
	private double education;
	private double friend;
	private double fruit;
	private double life;
	private double meat;
	private double mental;
	private double nuts;
	private double relationship;
	private double sleep;
	private double teeth;
	private double tension;
	private double water;

	// hearth test;
	private double age;
	private double have_genetic;
	private double hypertension;
	private double inflammation;
	private double insulin;
	private double nutrition;
	private double rate;
	private double steatorrhoeic_hepatosis;

	private int cluster;

	public DataPoint() {
		this.cluster = -1;
	}

	public DataPoint(Long userId, double alcohol, double animalfats, double artificialsweetener, double charcuterie,
			double fiberfood, double fizzydrink, double have_cancer, double height, double hepatit, double hpv,
			double insufficient, double job, double movement, double processedfood, double pylori, double redmeat,
			double smoke, double stres, double virus, double weight, double artificial_nourishment, double bloodsugar,
			double check_up, double cholesterol, double disease, double education, double friend, double fruit,
			double life, double meat, double mental, double nuts, double relationship, double sleep, double teeth,
			double tension, double water, double age, double have_genetic, double hypertension, double inflammation,
			double insulin, double nutrition, double rate, double steatorrhoeic_hepatosis) {
		this.userId = userId;
		this.alcohol = alcohol;
		this.animalfats = animalfats;
		this.artificialsweetener = artificialsweetener;
		this.charcuterie = charcuterie;
		this.fiberfood = fiberfood;
		this.fizzydrink = fizzydrink;
		this.have_cancer = have_cancer;
		this.height = height;
		this.hepatit = hepatit;
		this.hpv = hpv;
		this.insufficient = insufficient;
		this.job = job;
		this.movement = movement;
		this.processedfood = processedfood;
		this.pylori = pylori;
		this.redmeat = redmeat;
		this.smoke = smoke;
		this.stres = stres;
		this.virus = virus;
		this.weight = weight;
		this.artificial_nourishment = artificial_nourishment;
		this.bloodsugar = bloodsugar;
		this.check_up = check_up;
		this.cholesterol = cholesterol;
		this.disease = disease;
		this.education = education;
		this.friend = friend;
		this.fruit = fruit;
		this.life = life;
		this.meat = meat;
		this.mental = mental;
		this.nuts = nuts;
		this.relationship = relationship;
		this.sleep = sleep;
		this.teeth = teeth;
		this.tension = tension;
		this.water = water;
		this.age = age;
		this.have_genetic = have_genetic;
		this.hypertension = hypertension;
		this.inflammation = inflammation;
		this.insulin = insulin;
		this.nutrition = nutrition;
		this.rate = rate;
		this.steatorrhoeic_hepatosis = steatorrhoeic_hepatosis;
		this.cluster = -1;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public double getAnimalfats() {
		return animalfats;
	}

	public void setAnimalfats(double animalfats) {
		this.animalfats = animalfats;
	}

	public double getArtificialsweetener() {
		return artificialsweetener;
	}

	public void setArtificialsweetener(double artificialsweetener) {
		this.artificialsweetener = artificialsweetener;
	}

	public double getCharcuterie() {
		return charcuterie;
	}

	public void setCharcuterie(double charcuterie) {
		this.charcuterie = charcuterie;
	}

	public double getFiberfood() {
		return fiberfood;
	}

	public void setFiberfood(double fiberfood) {
		this.fiberfood = fiberfood;
	}

	public double getFizzydrink() {
		return fizzydrink;
	}

	public void setFizzydrink(double fizzydrink) {
		this.fizzydrink = fizzydrink;
	}

	public double getHave_cancer() {
		return have_cancer;
	}

	public void setHave_cancer(double have_cancer) {
		this.have_cancer = have_cancer;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getHepatit() {
		return hepatit;
	}

	public void setHepatit(double hepatit) {
		this.hepatit = hepatit;
	}

	public double getHpv() {
		return hpv;
	}

	public void setHpv(double hpv) {
		this.hpv = hpv;
	}

	public double getInsufficient() {
		return insufficient;
	}

	public void setInsufficient(double insufficient) {
		this.insufficient = insufficient;
	}

	public double getJob() {
		return job;
	}

	public void setJob(double job) {
		this.job = job;
	}

	public double getMovement() {
		return movement;
	}

	public void setMovement(double movement) {
		this.movement = movement;
	}

	public double getProcessedfood() {
		return processedfood;
	}

	public void setProcessedfood(double processedfood) {
		this.processedfood = processedfood;
	}

	public double getPylori() {
		return pylori;
	}

	public void setPylori(double pylori) {
		this.pylori = pylori;
	}

	public double getRedmeat() {
		return redmeat;
	}

	public void setRedmeat(double redmeat) {
		this.redmeat = redmeat;
	}

	public double getSmoke() {
		return smoke;
	}

	public void setSmoke(double smoke) {
		this.smoke = smoke;
	}

	public double getStres() {
		return stres;
	}

	public void setStres(double stres) {
		this.stres = stres;
	}

	public double getVirus() {
		return virus;
	}

	public void setVirus(double virus) {
		this.virus = virus;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getArtificial_nourishment() {
		return artificial_nourishment;
	}

	public void setArtificial_nourishment(double artificial_nourishment) {
		this.artificial_nourishment = artificial_nourishment;
	}

	public double getBloodsugar() {
		return bloodsugar;
	}

	public void setBloodsugar(double bloodsugar) {
		this.bloodsugar = bloodsugar;
	}

	public double getCheck_up() {
		return check_up;
	}

	public void setCheck_up(double check_up) {
		this.check_up = check_up;
	}

	public double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}

	public double getDisease() {
		return disease;
	}

	public void setDisease(double disease) {
		this.disease = disease;
	}

	public double getEducation() {
		return education;
	}

	public void setEducation(double education) {
		this.education = education;
	}

	public double getFriend() {
		return friend;
	}

	public void setFriend(double friend) {
		this.friend = friend;
	}

	public double getFruit() {
		return fruit;
	}

	public void setFruit(double fruit) {
		this.fruit = fruit;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getMeat() {
		return meat;
	}

	public void setMeat(double meat) {
		this.meat = meat;
	}

	public double getMental() {
		return mental;
	}

	public void setMental(double mental) {
		this.mental = mental;
	}

	public double getNuts() {
		return nuts;
	}

	public void setNuts(double nuts) {
		this.nuts = nuts;
	}

	public double getRelationship() {
		return relationship;
	}

	public void setRelationship(double relationship) {
		this.relationship = relationship;
	}

	public double getSleep() {
		return sleep;
	}

	public void setSleep(double sleep) {
		this.sleep = sleep;
	}

	public double getTeeth() {
		return teeth;
	}

	public void setTeeth(double teeth) {
		this.teeth = teeth;
	}

	public double getTension() {
		return tension;
	}

	public void setTension(double tension) {
		this.tension = tension;
	}

	public double getWater() {
		return water;
	}

	public void setWater(double water) {
		this.water = water;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getHave_genetic() {
		return have_genetic;
	}

	public void setHave_genetic(double have_genetic) {
		this.have_genetic = have_genetic;
	}

	public double getHypertension() {
		return hypertension;
	}

	public void setHypertension(double hypertension) {
		this.hypertension = hypertension;
	}

	public double getInflammation() {
		return inflammation;
	}

	public void setInflammation(double inflammation) {
		this.inflammation = inflammation;
	}

	public double getInsulin() {
		return insulin;
	}

	public void setInsulin(double insulin) {
		this.insulin = insulin;
	}

	public double getNutrition() {
		return nutrition;
	}

	public void setNutrition(double nutrition) {
		this.nutrition = nutrition;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getSteatorrhoeic_hepatosis() {
		return steatorrhoeic_hepatosis;
	}

	public void setSteatorrhoeic_hepatosis(double steatorrhoeic_hepatosis) {
		this.steatorrhoeic_hepatosis = steatorrhoeic_hepatosis;
	}

	public int getCluster() {
		return cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		DataPoint other = (DataPoint) obj;
		return  alcohol == other.alcohol && animalfats == other.animalfats
				&& artificialsweetener == other.artificialsweetener && charcuterie == other.charcuterie
				&& fiberfood == other.fiberfood && fizzydrink == other.fizzydrink && have_cancer == other.have_cancer
				&& height == other.height && hepatit == other.hepatit && hpv == other.hpv
				&& insufficient == other.insufficient && job == other.job && movement == other.movement
				&& processedfood == other.processedfood && pylori == other.pylori && redmeat == other.redmeat
				&& smoke == other.smoke && stres == other.stres && virus == other.virus && weight == other.weight
				&& artificial_nourishment == other.artificial_nourishment && bloodsugar == other.bloodsugar
				&& check_up == other.check_up && cholesterol == other.cholesterol && disease == other.disease
				&& education == other.education && friend == other.friend && fruit == other.fruit && life == other.life
				&& meat == other.meat && mental == other.mental && nuts == other.nuts
				&& relationship == other.relationship && sleep == other.sleep && teeth == other.teeth
				&& tension == other.tension && water == other.water && age == other.age
				&& have_genetic == other.have_genetic && hypertension == other.hypertension
				&& inflammation == other.inflammation && insulin == other.insulin && nutrition == other.nutrition
				&& rate == other.rate && steatorrhoeic_hepatosis == other.steatorrhoeic_hepatosis;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "("+alcohol + ", " + animalfats + ", " + artificialsweetener + ", " + ", " + charcuterie + ", " + fiberfood + ", " +
				fizzydrink + ", " + have_cancer + ", " + height + ", " + hepatit + ", " + hpv + ", " + 
				insufficient + ", " + job + ", " + movement + ", " + processedfood + ", " + pylori + ", " +
				redmeat + ", " + smoke + ", " + stres + ", " + virus + ", " + weight + ", " +
				artificial_nourishment + ", " + bloodsugar + ", " + check_up + ", " + cholesterol + ", " + disease + ", " +
				education + ", " + friend + ", " + fruit + ", " + life + ", " + meat + ", " +
				mental + ", " + nuts + ", " + relationship + ", " + sleep + ", " + teeth + ", " +
				tension + ", " + water + ", " + age + ", " + have_genetic + ", " + hypertension + ", " +
				inflammation + ", " + insulin + ", " + nutrition + ", " + rate + ", " + steatorrhoeic_hepatosis + ")";
	}
}
