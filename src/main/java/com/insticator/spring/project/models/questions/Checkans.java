package com.insticator.spring.project.models.questions;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insticator.spring.project.models.user.User;

@Entity
@Table(name = "checkanss")
public class Checkans {

	@EmbeddedId
    private CheckansId id;
	
	@ManyToOne
    @JoinColumn(name="checkbox_id", insertable=false, updatable=false)
	private Checkbox checkBox;
	
	@ManyToOne
    @JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	@ElementCollection
    @CollectionTable(name = "user_checkbox_answers", joinColumns = {@JoinColumn(name = "checkbox_id"), @JoinColumn(name = "user_id")})
    @Column
	private Set<String> answers;

	public Set<String> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<String> answers) {
		this.answers = answers;
	}

	public Checkbox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(Checkbox checkBox) {
		this.checkBox = checkBox;
	}

	public CheckansId getId() {
		return id;
	}

	public void setId(CheckansId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Checkans(CheckansId id, Set<String> answers, Checkbox checkBox, User user) {
		this.id = id;
		this.answers = answers;
		this.checkBox = checkBox;
		this.user = user;
	}

	public Checkans() {
	}
}
