package com.project.tez.requests;

import lombok.Data;

@Data
public class RiskFormCreateRequest {

	private Long userId;
	private int haveCancer;
	private int smoke;
	private boolean redmeat;
	private boolean charcuterie;
	private boolean animalfats;
	private boolean fiberfood;
	private boolean insufficient;
	private boolean processedfood;
	private boolean fizzydrink;
	private int weight;
	private int height;
	private boolean artificialsweetener;
	private boolean hepatit;
	private boolean virus;
	private boolean hpv;
	private boolean pylori;
	private int alcohol;
	private int stres;
	private int job;
	private int movement;
	
}
