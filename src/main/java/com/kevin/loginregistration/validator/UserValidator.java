package com.kevin.loginregistration.validator;

import org.springframework.validation.Validator;

import com.kevin.loginregistration.models.User;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}
