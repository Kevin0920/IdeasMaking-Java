package com.kevin.loginregistration.controllers;

import java.security.Principal;

import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kevin.loginregistration.models.*;
import com.kevin.loginregistration.services.UserService;
import com.kevin.loginregistration.validator.UserValidator;

@Controller
public class UserController {
	
	private UserValidator userValidator;
	
	
	private UserService userService;
	
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	@RequestMapping("/login")
	public String loginAndReg(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required = false) String error, @RequestParam(value= "logout", required = false) String logout, Model model) {
		if (error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		}
		if (logout != null) {
			model.addAttribute("logoutMessage", "Logout successful!!");
		}
		return "loginPage";
	}
	
	
	@PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        // NEW
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "loginPage";
        }
        
        userService.saveWithUserRole(user);
        return "redirect:/home";
    }
	
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        // 1
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        return "home";
    }
}
