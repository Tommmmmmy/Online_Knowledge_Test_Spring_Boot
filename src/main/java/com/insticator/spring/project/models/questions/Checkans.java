package com.insticator.spring.project.models.questions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insticator.spring.project.models.user.User;

@Entity
@Table(name = "checkanss")
public class Checkans {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String statement;
	
	@ManyToOne
    @JoinColumn(name = "checkbox_id")
	@JsonIgnore
	private Checkbox checkBox;
	
	
}
