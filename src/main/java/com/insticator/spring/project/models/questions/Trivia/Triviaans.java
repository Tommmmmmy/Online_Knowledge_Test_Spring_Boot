package com.insticator.spring.project.models.questions.Trivia;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.insticator.spring.project.models.user.User;

@Entity
@Table(name = "triviaanss")
public class Triviaans {
	@EmbeddedId
    private TriviaansId id;
	
	@ManyToOne
    @JoinColumn(name="trivia_id", insertable=false, updatable=false)
	private Trivia trivia;
	
	@ManyToOne
    @JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	@ElementCollection
    @CollectionTable(name = "user_trivia_answers", joinColumns = {@JoinColumn(name = "trivia_id"), @JoinColumn(name = "user_id")})
    @Column
	private String answer;

	public TriviaansId getId() {
		return id;
	}

	public void setId(TriviaansId id) {
		this.id = id;
	}

	public Trivia getTrivia() {
		return trivia;
	}

	public void setTrivia(Trivia trivia) {
		this.trivia = trivia;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Triviaans(TriviaansId id, Trivia trivia, User user, String answer) {
		this.id = id;
		this.trivia = trivia;
		this.user = user;
		this.answer = answer;
	}

	public Triviaans() {
	}
	
	
}
