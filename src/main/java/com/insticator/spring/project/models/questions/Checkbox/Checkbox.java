package com.insticator.spring.project.models.questions.Checkbox;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insticator.spring.project.models.questions.Question;
import com.insticator.spring.project.models.user.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "checkboxs")
public class Checkbox extends Question{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String question;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private User uUser;
	
	@ElementCollection
    @CollectionTable(name = "checkbox_option", joinColumns = @JoinColumn(name = "checkbox_id"))
    @Column(name = "options")
	private Set<String> options;

	public Checkbox() {
		super(3);
		// TODO Auto-generated constructor stub
	}

	public User getuUser() {
		return uUser;
	}

	public void setuUser(User uUser) {
		this.uUser = uUser;
	}



	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<String> getOptions() {
		return options;
	}

	public void setOptions(Set<String> options) {
		this.options = options;
	}

	public Checkbox(String question, User uUser, Set<String> options) {
		super(3);
		this.question = question;
		this.uUser = uUser;
		this.options = options;
	}


	@Override
	public String toString() {
		String res = "Checkbox [id=" + id + "question=" + question + "options=";
		for(String opt : options ) {
			res += opt + ",";
		}
		res += "id=" + id;
		return res;
	}
	
	
}	
	
