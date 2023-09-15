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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name="forum")
@Data
public class Forum {

	@Id
	@SequenceGenerator(name = "for_id_generator", sequenceName = "for_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "for_id_generator")
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	
	@Column(name = "FORUM_NAME", columnDefinition = "VARCHAR2(30)", nullable = false)
	private String forumName;
	
	@Column(name = "MESSAGE", columnDefinition = "VARCHAR2(750)")
	private String message;
	
	@Column(name = "MESSAGE_DATE", columnDefinition = "DATE DEFAULT TO_DATE(SYSDATE, 'DD-MM-YYYY')")
	private Date messageDate; 
	
}
