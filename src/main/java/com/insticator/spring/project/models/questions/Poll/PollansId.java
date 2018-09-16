package com.insticator.spring.project.models.questions.Poll;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PollansId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3545855176554540569L;
	@Column(name = "user_id")
    private int userId;
    @Column(name = "poll_id")
    private int pollId;
	public PollansId(int userId, int pollId) {
		this.userId = userId;
		this.pollId = pollId;
	}
	public PollansId() {
	}
}
