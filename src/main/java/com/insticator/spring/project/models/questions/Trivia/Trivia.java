package com.insticator.spring.project.models.questions.Trivia;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insticator.spring.project.models.questions.Question;
import com.insticator.spring.project.models.user.User;

@Entity
@Table(name = "trivias")
public class Trivia extends Question{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String question;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private User uUser;
	
	@ElementCollection
    @CollectionTable(name = "trivia_option", joinColumns = @JoinColumn(name = "trivia_id"))
    @Column(name = "options")
	private Set<String> options;

	private String correctAns;

	public Trivia() {
		super(1);
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

	public String getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}

	public Trivia(String question, User uUser, Set<String> options, String correctAns) {
		super(1);
		this.question = question;
		this.uUser = uUser;
		this.options = options;
		this.correctAns = correctAns;
	}
}	
	
