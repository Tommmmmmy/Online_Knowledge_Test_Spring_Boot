package com.insticator.spring.project.models.questions.Poll;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PollansId {
	@Column(name = "user_id")
    private int userId;
    @Column(name = "poll_id")
    private int PollId;
	public PollansId(int userId, int PollId) {
		this.userId = userId;
		this.PollId = PollId;
	}
	public PollansId() {
	}
}
