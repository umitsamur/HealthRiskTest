package com.project.tez.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name="clusters")
@Data
public class Cluster {
	
	@Id
	@SequenceGenerator(name="cluster_id_generator",sequenceName = "CLUSTER_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cluster_id_generator")
	private Long id;
	
	private String clusterName;

}
