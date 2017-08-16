package com.patrick.loginregistration.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrick.loginregistration.models.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long>{

		List<Role> findAll();
		List<Role> findByName(String name);
}
