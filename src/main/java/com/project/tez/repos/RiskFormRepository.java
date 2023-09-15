package com.project.tez.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.tez.entities.RiskForm;

public interface RiskFormRepository extends JpaRepository<RiskForm, Long> {
	
	boolean existsByUserId(Long userId);
	
	List<RiskForm> findByUserId(Long userId);
	
	@Query(value = "select rf.user_id, rf.alcohol, rf.animalfats, rf.artificialsweetener, rf.charcuterie, rf.fiberfood, rf.fizzydrink, rf.have_cancer, " + 
	" rf.height, rf.hepatit, rf.hpv, rf.insufficient, rf.job, rf.movement, rf.processedfood, rf.pylori, rf.redmeat, rf.smoke, rf.stres, rf.virus, rf.weight, "+ 
	" ra.ARTIFICIAL_NOURISHMENT, ra.bloodsugar, ra.check_up, ra.cholesterol, ra.disease, ra.education, ra.friend, ra.fruit, ra.life, ra.meat, ra.mental, ra.nuts, " +
	" ra.relationship, ra.sleep, ra.teeth, ra.tension, ra.water, htf.age, htf.have_genetic, htf.hypertension, htf.inflammation, htf.insulin, htf.nutrition, "+
	" htf.rate, htf.STEATORRHOEIC_HEPATOSIS "
	+ "from risk_form rf inner join real_age ra on rf.user_id = ra.user_id inner join hearth_test_form htf on htf.user_id = ra.user_id where "+
	 " ra.id=(select max(id) from real_age where user_id = ra.user_id) and htf.id = (select max(id) from hearth_test_form where user_id = htf.user_id) and "+
			"rf.id = (select max(id) from risk_form where user_id = rf.user_id)",nativeQuery = true)
	List<Object[]> getAllDataWithJoins();
}
