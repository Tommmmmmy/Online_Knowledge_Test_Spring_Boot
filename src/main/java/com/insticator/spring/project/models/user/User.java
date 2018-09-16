package com.insticator.spring.project.models.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.insticator.spring.project.models.questions.Question;
import com.insticator.spring.project.models.questions.Checkbox.Checkbox;
import com.insticator.spring.project.models.questions.Poll.Poll;
import com.insticator.spring.project.models.questions.Trivia.Trivia;

@Entity
@Table(name = "users")
public class User implements Serializable{
	
	private static final long serialVersionUID = 6832006422622219737L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "uUser", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Trivia> tQuestions;
	
	@OneToMany(mappedBy = "uUser", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Checkbox> cQuestions;
	
	@OneToMany(mappedBy = "uUser", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Poll> pQuestions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Trivia> gettQuestions() {
		return tQuestions;
	}

	public void settQuestions(Set<Trivia> tQuestions) {
		this.tQuestions = tQuestions;
	}

	public Set<Checkbox> getcQuestions() {
		return cQuestions;
	}

	public void setcQuestions(Set<Checkbox> cQuestions) {
		this.cQuestions = cQuestions;
	}

	public Set<Poll> getpQuestions() {
		return pQuestions;
	}

	public void setpQuestions(Set<Poll> pQuestions) {
		this.pQuestions = pQuestions;
	}
	
	public User() {
		
	}

	public User(String name) {
		this.name = name;
	}

	public User(int id, String name, Set<Trivia> tQuestions, Set<Checkbox> cQuestions, Set<Poll> pQuestions) {
		this.id = id;
		this.name = name;
		this.tQuestions = tQuestions;
		this.cQuestions = cQuestions;
		this.pQuestions = pQuestions;
	}
	
	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
//	}
//	
}