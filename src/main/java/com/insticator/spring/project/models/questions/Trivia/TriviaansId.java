package com.insticator.spring.project.models.questions.Trivia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TriviaansId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6045226360521301109L;
	@Column(name = "user_id")
    private int userId;
    @Column(name = "trivia_id")
    private int triviaId;
	public TriviaansId(int userId, int checkboxId) {
		this.userId = userId;
		this.triviaId = checkboxId;
	}
	public TriviaansId() {
	}
}
