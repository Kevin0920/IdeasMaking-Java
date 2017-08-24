package com.kevin.loginregistration.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevin.loginregistration.models.Idea;
import com.kevin.loginregistration.models.User;

@Repository
public interface IdeaRepo extends CrudRepository<Idea, Long> {

	void save(User user);

}
