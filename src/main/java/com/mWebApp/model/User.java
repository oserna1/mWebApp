package com.mWebApp.model;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

		private long id;
		
		@NotEmpty @Email
		private String email;
		
		@Size(min=5, max=15) @NotEmpty
		private String password;
		
		private String username;
		
		private List<Mood> mood_List;
		
		public User(){
			id=0;
		}
		
		public User(long id, String email, String username, String password){
			this.id = id;
			this.email = email;
			this.password = password;
			this.username = username;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "User [id=" + id + "username= " + username + ", email=" + email + ", password=" + password + "]";
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public List<Mood> getMood_Set() {
			return mood_List;
		}

		public void setMood_List(List<Mood> mood_List) {
			this.mood_List = mood_List;
		}
		

		
}

