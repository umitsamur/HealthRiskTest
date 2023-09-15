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
@Table(name = "RiskForm")
@Data
public class RiskForm {

	@Id
	@SequenceGenerator(name="cancerrisk_generator",sequenceName = "CANCERRISKT_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cancerrisk_generator")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(name = "have_cancer",  columnDefinition = "VARCHAR2(1) CHECK(have_cancer IN ('0','1'))")
	private int haveCancer;
	
	@Column(name = "smoke",  columnDefinition = "VARCHAR2(1) CHECK(smoke IN ('0','1'))")
	private int smoke;
	
	@Column(name = "redmeat",  columnDefinition = "VARCHAR2(1) CHECK(redmeat IN ('0','1'))")
	private int redmeat;
	
	@Column(name = "charcuterie",  columnDefinition = "VARCHAR2(1) CHECK(charcuterie IN ('0','1'))")
	private int charcuterie;
	
	@Column(name = "animalfats",  columnDefinition = "VARCHAR2(1) CHECK(animalfats IN ('0','1'))")
	private int animalfats;
	
	@Column(name = "fiberfood",  columnDefinition = "VARCHAR2(1) CHECK(fiberfood IN ('0','1'))")
	private int fiberfood;
	
	@Column(name = "insufficient",  columnDefinition = "VARCHAR2(1) CHECK(insufficient IN ('0','1'))")
	private int insufficient;
	
	@Column(name = "processedfood",  columnDefinition = "VARCHAR2(1) CHECK(processedfood IN ('0','1'))")
	private int processedfood;
	
	@Column(name = "fizzydrink",  columnDefinition = "VARCHAR2(1) CHECK(fizzydrink IN ('0','1'))")
	private int fizzydrink;
	
	@Column(name = "artificialsweetener",  columnDefinition = "VARCHAR2(1) CHECK(artificialsweetener IN ('0','1'))")
	private int artificialsweetener;
	
	@Column(name = "weight", columnDefinition = "NUMBER(3)")
	private int weight;
	
	@Column(name = "height", columnDefinition = "NUMBER(3)")
	private int height;
	
	@Column(name = "hepatit",  columnDefinition = "VARCHAR2(1) CHECK(hepatit IN ('0','1'))")
	private int hepatit;
	
	@Column(name = "virus",  columnDefinition = "VARCHAR2(1) CHECK(virus IN ('0','1'))")
	private int virus;
	
	@Column(name = "hpv",  columnDefinition = "VARCHAR2(1) CHECK(hpv IN ('0','1'))")
	private int hpv;
	
	@Column(name = "pylori",  columnDefinition = "VARCHAR2(1) CHECK(pylori IN ('0','1'))")
	private int pylori;
	
	@Column(name = "alcohol",  columnDefinition = "VARCHAR2(1) CHECK(alcohol IN ('0','1','2','3'))")
	private int alcohol;
	
	@Column(name = "stres",  columnDefinition = "VARCHAR2(1) CHECK(stres IN ('0','1'))")
	private int stres;
	
	@Column(name = "job",  columnDefinition = "VARCHAR2(1) CHECK(job IN ('0','1'))")
	private int job;
	
	@Column(name = "movement",  columnDefinition = "VARCHAR2(1) CHECK(movement IN ('0','1'))")
	private int movement;
	
}
