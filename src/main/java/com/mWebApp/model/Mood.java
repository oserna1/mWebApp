package com.mWebApp.model;

import java.security.Timestamp;

public class Mood {
	
	private long id;
	
	private int moodRange;
	
	private String description;
	
	private Timestamp ts;
	
	private Long uId;
	
	public Mood() {
		id=0;
	}
	
	public Mood(long id, int moodRange, String description, Timestamp ts, Long uId) {
		this.id = id;
		this.moodRange = moodRange;
		this.description = description;
		this.ts = ts;
		this.uId = uId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMoodRange() {
		return moodRange;
	}

	public void setMoodRange(int moodRange) {
		this.moodRange = moodRange;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}


}
