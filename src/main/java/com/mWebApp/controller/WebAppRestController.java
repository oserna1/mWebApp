package com.mWebApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mWebApp.model.Mood;
import com.mWebApp.model.User;
import com.mWebApp.service.MoodService;
import com.mWebApp.service.UserService;


@RestController
public class WebAppRestController {
	
	@Autowired
    UserService userService;
	
	@Autowired
	MoodService moodService;
	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
  //-------------------Retrieve Single User--------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
  //-------------------Create a User--------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUsername());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
  //------------------- Update a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setEmail(user.getEmail());
         
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
    
    
  //------------------- Delete a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
  //------------------- Delete All Users --------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
  //-------------------Retrieve Moods by UserId--------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mood>> getMoods(@PathVariable("id") Long id) {
        System.out.println("Fetching Moods with uid " + id);
        List<Mood> moods = moodService.findByUid(id);
        if (moods == null) {
            System.out.println("Moods with id: " + id + " not found");
            return new ResponseEntity<List<Mood>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Mood>>(moods, HttpStatus.OK);
    }
    
//-------------------Retrieve Single Mood--------------------------------------------------------
   /* 
    @RequestMapping(value = "/track/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mood> getMood(@PathVariable("id") long id) {
        System.out.println("Fetching Mood with id " + id);
        Mood mood = moodService.findById(id);
        if (mood == null) {
            System.out.println("Mood with id " + id + " not found");
            return new ResponseEntity<Mood>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mood>(mood, HttpStatus.OK);
    }
    */
  //-------------------Create a Mood--------------------------------------------------------
    
    @RequestMapping(value = "/track/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMood(@RequestBody Mood mood,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + mood.getDescription());
 
        if (moodService.isMoodExist(mood)) {
            System.out.println("A mood with description " + mood.getDescription() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        moodService.saveMood(mood);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/track/{id}").buildAndExpand(mood.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
  //------------------- Update a Mood --------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mood> updateMood(@PathVariable("id") long id, @RequestBody Mood mood) {
        System.out.println("Updating Mood " + id);
         
        Mood currentMood = moodService.findById(id);
         
        if (currentMood==null) {
            System.out.println("Mood with id " + id + " not found");
            return new ResponseEntity<Mood>(HttpStatus.NOT_FOUND);
        }
 
        currentMood.setMoodRange(mood.getMoodRange());
        currentMood.setDescription(mood.getDescription());
        currentMood.setTs(mood.getTs());
         
        moodService.updateMood(currentMood);
        return new ResponseEntity<Mood>(currentMood, HttpStatus.OK);
    }
    
    
  //------------------- Delete a Mood --------------------------------------------------------
    
    @RequestMapping(value = "/track/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mood> deleteMood(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Mood with id " + id);
 
        Mood mood = moodService.findById(id);
        if (mood == null) {
            System.out.println("Unable to delete. Mood with id " + id + " not found");
            return new ResponseEntity<Mood>(HttpStatus.NOT_FOUND);
        }
 
        moodService.deleteMoodById(id);
        return new ResponseEntity<Mood>(HttpStatus.NO_CONTENT);
    }
    
  //------------------- Delete All Moods --------------------------------------------------------
    
    @RequestMapping(value = "/track/", method = RequestMethod.DELETE)
    public ResponseEntity<Mood> deleteAllMoods() {
        System.out.println("Deleting All Moods");
 
        moodService.deleteAllMoods();
        return new ResponseEntity<Mood>(HttpStatus.NO_CONTENT);
    }

}
