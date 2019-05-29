package com.mWebApp.service;

import java.sql.Timestamp;
import java.util.List;

import com.mWebApp.model.Mood;

public interface MoodService {
	Mood findById(long id);
	
	Mood  findByTimestamp(Timestamp ts);
	
	void saveMood(Mood mood);
	
	void updateMood(Mood mood);
	
	void deleteMoodById(long id);

	List<Mood> findAllMoods(); 
	
	List<Mood> findByUid(long id);
	
	void deleteAllMoods();
	
	public boolean isMoodExist(Mood	mood);
	
}
