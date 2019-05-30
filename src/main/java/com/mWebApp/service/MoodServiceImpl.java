package com.mWebApp.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.mWebApp.model.Mood;

@Service
public class MoodServiceImpl  implements MoodService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Mood> moods;
	
	static{
		moods = populateDummyMoods();
	}
	
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
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		mood.setTs(timestamp);
		mood.setId(counter.incrementAndGet());
		moods.add(mood);
	}
	
	public void updateMood(Mood currentMood, Mood mood) {
		int index = moods.indexOf(currentMood);
		moods.set(index, currentMood);
		currentMood.setMoodRange(mood.getMoodRange());
        currentMood.setDescription(mood.getDescription());
        currentMood.setTs(mood.getTs());
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
	
	public List<Mood> findByUid(long uid) {
		List<Mood> MoodsUid = new ArrayList<Mood>();
		for(Mood mood: moods) {
			if(mood.getuId()==uid) {
				MoodsUid.add(mood);
			}
		}
		return MoodsUid;
	}
	
	private static List<Mood> populateDummyMoods(){
		List<Mood> moods = new ArrayList<Mood>();
		moods.add(new Mood(counter.incrementAndGet(),4, "alright", null, (long) 2 ));
		moods.add(new Mood(counter.incrementAndGet(),0, "depressed", null, (long) 1));
		moods.add(new Mood(counter.incrementAndGet(),9, "happy", null, (long) 3));
		moods.add(new Mood(counter.incrementAndGet(),9, "happy", null, (long) 3));
		moods.add(new Mood(counter.incrementAndGet(),7, "aight", null, (long) 3));
		moods.add(new Mood(counter.incrementAndGet(),5, "meh", null, (long) 3));
		moods.add(new Mood(counter.incrementAndGet(),5, "meh", null, (long) 2));
		moods.add(new Mood(counter.incrementAndGet(),5, "meh", null, (long) 1));
		return moods;
	}



}
