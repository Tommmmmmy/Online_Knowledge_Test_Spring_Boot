package com.insticator.spring.project.models.questions;

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
    @CollectionTable(name = "question_option", joinColumns = @JoinColumn(name = "trivia_id"))
    @Column(name = "options")
	private Set<String> options;
	
	@ElementCollection
    @CollectionTable(name = "question_answer", joinColumns = @JoinColumn(name = "trivia_id"))
    @Column(name = "answers")
	private Set<String> answers;
	
	@ElementCollection
    @CollectionTable(name = "question_correct_ans", joinColumns = @JoinColumn(name = "trivia_id"))
    @Column(name = "correct_ans")
	private Set<String> correctAns;

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

	public Set<String> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<String> answers) {
		this.answers = answers;
	}

	public Set<String> getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(Set<String> correctAns) {
		this.correctAns = correctAns;
	}

	public Trivia(String question, Set<String> options, Set<String> answers, Set<String> correctAns) {
		super(1);
		this.question = question;
		this.options = options;
		this.answers = answers;
		this.correctAns = correctAns;
	}
}	
	
