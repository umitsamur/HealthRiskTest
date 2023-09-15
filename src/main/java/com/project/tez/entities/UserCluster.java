package com.project.tez.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_cluster")
@Data
public class UserCluster {
	
	@Id
	@SequenceGenerator(name="user_cluster_id_generator",sequenceName = "USER_CLUSTER_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_cluster_id_generator")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_constraint_user"))
    private User user;
 
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cluster_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_constraint_cluster"))
    private Cluster cluster;
	
}
