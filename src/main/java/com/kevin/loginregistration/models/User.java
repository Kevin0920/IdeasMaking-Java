package com.kevin.loginregistration.models;

import javax.persistence.*;
import javax.validation.constraints.Size;


import java.util.*;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    @Size(min=1, message="First name must be at least 1 character")
    private String firstname;
    @Size(min=1, message="Last name must be at least 1 character")
    private String lastname;
    @Size(min=1, message="Alias must be at least 1 character")
    private String alias;
    private String email;
    @Size(min=8, message = "Pass must be greater than 8")
    private String password;
    @Transient
    private String passwordConfirmation;
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_ideas", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "idea_id")
        )
    private List<Idea> ideas;
    
    @OneToMany(mappedBy="poster", fetch = FetchType.LAZY)
    private List<Idea> postIdeas;
    
    
    
    
    
    
    
  
    public List<Idea> getPostIdeas() {
		return postIdeas;
	}

	public void setPostIdeas(List<Idea> postIdeas) {
		this.postIdeas = postIdeas;
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}
	
    public User() {
    }

    public Long getId() {
        return id;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@PrePersist
    protected void onCreate(){
    this.setCreatedAt(new Date());
    }

    @PreUpdate
    protected void onUpdate(){
    this.setUpdatedAt(new Date());
    }
}