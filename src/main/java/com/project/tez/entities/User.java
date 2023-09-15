package com.project.tez.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Entity
@Table(name="USERS")
@Data
public class User {
	
	@Id
	@SequenceGenerator(name="pat_id_generator",sequenceName = "PAT_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pat_id_generator")
	private Long id;
	@Column(name = "name", nullable = false, columnDefinition = "VARCHAR2(25)")
	private String name;
	@Column(name = "surname", nullable = false, columnDefinition = "VARCHAR2(25)")
	private String surname;
	@Column(name = "password")
	private String password;
	@Column(name = "email", nullable = false, columnDefinition = "VARCHAR2(25)", unique = true)
	private String email;
	@Column(name = "role",  columnDefinition = "VARCHAR2(25)")
	private String role;
	@Column(name = "gender",  columnDefinition = "NUMBER(1) CHECK(gender IN (0,1))")
	private int gender;
	@Column(name = "contact_number", columnDefinition = "VARCHAR2(12)")
	private String contactNumber;
	@Column(name = "birth_date", columnDefinition = "DATE DEFAULT TO_DATE(SYSDATE, 'DD-MM-YYYY')")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@Column(name = "occupation", columnDefinition = "VARCHAR2(50)")
	private String occupation;
}
