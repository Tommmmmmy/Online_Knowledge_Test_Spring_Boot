package com.insticator.spring.project.models.questions.Trivia;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TriviaansId {
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
