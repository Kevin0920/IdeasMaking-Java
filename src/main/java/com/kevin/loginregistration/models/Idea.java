package com.kevin.loginregistration.models;

import java.util.List;

import javax.persistence.*;

import com.kevin.loginregistration.models.User;



@Entity
@Table(name="ideas")
public class Idea {
 	@Id
    @GeneratedValue
    private Long id;
 	private String message;
 	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    	name = "users_ideas",
    	joinColumns = @JoinColumn(name = "idea_id"),
    	inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User poster;
    
    
    public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public Idea() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
    
    
    
    
    
    
    
    
    
}
