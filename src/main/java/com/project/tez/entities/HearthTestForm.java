package com.project.tez.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name = "HearthTestForm")
@Data
public class HearthTestForm {
	
	@Id
	@SequenceGenerator(name="hearthtest_generator",sequenceName = "HEARTHTEST_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hearthtest_generator")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(name = "have_genetic",  columnDefinition = "VARCHAR2(1) CHECK(have_genetic IN ('0','1','2'))")
	private int haveGenetic;
	
	@Column(name = "age",  columnDefinition = "VARCHAR2(1) CHECK(age IN ('0','1','2'))")
	private int age;
	
	@Column(name = "smoke",  columnDefinition = "VARCHAR2(1) CHECK(smoke IN ('0','1','2','3'))")
	private int smoke;
	
	@Column(name = "nutrition",  columnDefinition = "VARCHAR2(1) CHECK(nutrition IN ('0','1','2'))")
	private int nutrition;
	
	@Column(name = "insulin",  columnDefinition = "VARCHAR2(1) CHECK(insulin IN ('0','1','2','3'))")
	private int insulin;
	
	@Column(name = "cholesterol",  columnDefinition = "VARCHAR2(1) CHECK(cholesterol IN ('0','1'))")
	private int cholesterol;
	
	@Column(name = "steatorrhoeic_hepatosis",  columnDefinition = "VARCHAR2(1) CHECK(steatorrhoeic_hepatosis IN ('0','1','2'))")
	private int steatorrhoeicHepatosis;
	
	@Column(name = "inflammation",  columnDefinition = "VARCHAR2(1) CHECK(inflammation IN ('0','1'))")
	private int inflammation;
	
	@Column(name = "rate", columnDefinition = "VARCHAR2(1) CHECK(rate IN ('0','1','2'))")
	private int rate;
	
	@Column(name = "hypertension", columnDefinition = "VARCHAR2(1) CHECK(hypertension IN ('0','1','2'))")
	private int hypertension;
	
	@Column(name = "movement", columnDefinition = "VARCHAR2(1) CHECK(movement IN ('0','1','2','3'))")
	private int movement;
	
	@Column(name = "stres", columnDefinition = "VARCHAR2(1) CHECK(stres IN ('0','1','2'))")
	private int stres;

}
