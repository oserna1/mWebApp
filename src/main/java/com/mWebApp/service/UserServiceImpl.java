package com.mWebApp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.mWebApp.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users= populateDummyUsers();
	

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User currentUser, User user) {
		int index = users.indexOf(currentUser);
		users.set(index, currentUser);
		currentUser.setUsername(user.getUsername());
		currentUser.setPassword(user.getPassword());
        currentUser.setEmail(user.getEmail());
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}
	
	public boolean isAdmin(User user) {
		if("oscar".equals(user.getUsername())){
			return true;
		}else {
			return false;
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"sam@abc.com", "sam", "sam123"));
		users.add(new User(counter.incrementAndGet(),"tomy@abc.com", "tomy", "tomy123"));
		users.add(new User(counter.incrementAndGet(),"kelly@abc.com", "kelly", "kelly123"));
		return users;
	}

}
