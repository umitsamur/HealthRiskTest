package com.project.tez.entities;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name = "ForumPost")
@Data
public class ForumPost {

	@Id
	@SequenceGenerator(name = "forum_post_id_generator", sequenceName = "forum_post_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forum_post_id_generator")
	private Long id;
	
	@Column(name = "title", columnDefinition = "VARCHAR2(30)")
	private String title;
	
	@Column(name="description", columnDefinition = "VARCHAR2(750)")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "forum_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Forum forum;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_cluster_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserCluster userCluster;
	
	@Column(name = "created_date", columnDefinition = "DATE DEFAULT TO_DATE(SYSDATE, 'DD-MM-YYYY')")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
}
