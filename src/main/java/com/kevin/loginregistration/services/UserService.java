package com.kevin.loginregistration.services;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kevin.loginregistration.models.Idea;
import com.kevin.loginregistration.models.User;
import com.kevin.loginregistration.repositories.IdeaRepo;
import com.kevin.loginregistration.repositories.UserRepo;

@Service
public class UserService {
	
	private UserRepo userRepo;
	private IdeaRepo ideaRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    
    public UserService(UserRepo userRepo, IdeaRepo ideaRepo, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepo = userRepo;
        this.ideaRepo = ideaRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }    
    
    // 3
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }


	public List<Idea> allIdeas() {
		return (List<Idea>) ideaRepo.findAll();
	}


	public void createIdea(Idea idea) {
		ideaRepo.save(idea);
		
	}


	public Object findOneUser(Long id) {
		return userRepo.findOne(id);
	}


	public void delete(Long id) {
		ideaRepo.delete(id);	
	}


	public void ideaLike(Long id, Long user_id) {
		User user = userRepo.findOne(id);
		Idea idea = ideaRepo.findOne(user_id);
		idea.getUsers().add(user);
		ideaRepo.save(user);
		
	}


	public Idea findOneIdea(Long id) {
		return ideaRepo.findOne(id);
	}


	public void saveUser(User user) {
		userRepo.save(user);
	}


	public List<User> allUsers() {
	
		return (List<User>) userRepo.findAll();
	}
}

