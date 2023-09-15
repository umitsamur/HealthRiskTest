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
@Table(name = "Real_Age")
@Data
public class RealAgeForm {
	
	@Id
	@SequenceGenerator(name="realage_generator",sequenceName = "REALAGE_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "realage_generator")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(name = "education",  columnDefinition = "VARCHAR2(1) CHECK(education IN ('0','1','2'))")
	private int education;
	
	@Column(name = "disease",  columnDefinition = "VARCHAR2(1) CHECK(disease IN ('0','1'))")
	private int disease;
	
	@Column(name = "smoke",  columnDefinition = "VARCHAR2(1) CHECK(smoke IN ('0','1','2','3'))")
	private int smoke;
	
	@Column(name = "alcohol",  columnDefinition = "VARCHAR2(1) CHECK(alcohol IN ('0','1','2','3'))")
	private int alcohol;
	
	@Column(name = "movement",  columnDefinition = "VARCHAR2(1) CHECK(movement IN ('0','1','2','3'))")
	private int movement;
	
	@Column(name = "sleep",  columnDefinition = "VARCHAR2(1) CHECK(sleep IN ('0','1','2'))")
	private int sleep;
	
	@Column(name = "relationship",  columnDefinition = "VARCHAR2(1) CHECK(relationship IN ('0','1','2','3','4'))", updatable = true)
	private int relationship;
	
	@Column(name = "life",  columnDefinition = "VARCHAR2(1) CHECK(life IN ('0','1','2','3'))")
	private int life;
	
	@Column(name = "mental", columnDefinition = "VARCHAR2(1) CHECK(mental IN ('0','1','2','3'))")
	private int mental;
	
	@Column(name = "friend", columnDefinition = "VARCHAR2(1) CHECK(friend IN ('0','1','2'))")
	private int friend;
	
	@Column(name = "check_up", columnDefinition = "VARCHAR2(1) CHECK(check_up IN ('0','1'))")
	private int check_up;
	
	@Column(name = "teeth", columnDefinition = "VARCHAR2(1) CHECK(teeth IN ('0','1'))")
	private int teeth;
	
	@Column(name = "tension", columnDefinition = "VARCHAR2(1) CHECK(tension IN ('0','1','2','3','4'))")
	private int tension;
	
	@Column(name = "bloodsugar", columnDefinition = "VARCHAR2(1) CHECK(bloodsugar IN ('0','1','2'))")
	private int bloodsugar;
	
	@Column(name = "cholesterol", columnDefinition = "VARCHAR2(1) CHECK(cholesterol IN ('0','1','2'))")
	private int cholesterol;
	
	@Column(name = "weight", columnDefinition = "VARCHAR2(1) CHECK(weight IN ('0','1','2','3','4'))")
	private int weight;
	
	@Column(name = "meat", columnDefinition = "VARCHAR2(1) CHECK(meat IN ('0','1','2','3'))")
	private int meat;
	
	@Column(name = "fruit", columnDefinition = "VARCHAR2(1) CHECK(fruit IN ('0','1','2','3'))")
	private int fruit;
	
	@Column(name = "water", columnDefinition = "VARCHAR2(1) CHECK(water IN ('0','1','2','3'))")
	private int water;
	
	@Column(name = "nuts", columnDefinition = "VARCHAR2(1) CHECK(nuts IN ('0','1','2'))")
	private int nuts;
	
	@Column(name = "artificial_nourishment", columnDefinition = "VARCHAR2(1) CHECK(artificial_nourishment IN ('0','1','2'))")
	private int artificial_nourishment;
	

}
