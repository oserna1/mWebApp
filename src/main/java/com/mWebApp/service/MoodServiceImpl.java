package com.mWebApp.service;

import java.security.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.mWebApp.model.Mood;

public class MoodServiceImpl  implements MoodService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	public List<Mood> moods;
	
	public List<Mood> findAllMoods() {
		return moods;	
	}
	
	public Mood findById(long id) {
		for(Mood mood: moods) {
			if(mood.getId() == id) {
				return mood;
			}
		}
		return null;
	}
	
	public Mood findByTimestamp(Timestamp ts) {
		for (Mood mood: moods) {
			if(mood.getTs().equals(ts)) {
				return mood;
			}
		}
		return null;
	}
	
	public void saveMood(Mood mood) {
		mood.setId(counter.incrementAndGet());
		moods.add(mood);
	}
	
	public void updateMood(Mood mood) {
		int index = moods.indexOf(mood);
		moods.set(index, mood);
	}
	
	public void deleteMoodById(long id) {
		
		for (Iterator<Mood> iterator = moods.iterator(); iterator.hasNext(); ) {
		    Mood moods = iterator.next();
		    if (moods.getId() == id) {
		        iterator.remove();
		    }
		}
	}
	
	public boolean isMoodExist(Mood	mood) {
		return findByTimestamp(mood.getTs())!=null;
	}
	
	public void deleteAllMoods(){
		moods.clear();
	}

}
