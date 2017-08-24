package com.kevin.loginregistration.controllers;

import java.security.Principal;

import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.kevin.loginregistration.models.*;
import com.kevin.loginregistration.services.UserService;
import com.kevin.loginregistration.validator.UserValidator;
import com.kevin.loginregistration.models.Idea;
import com.kevin.loginregistration.models.User;


@Controller
public class UserController {
	private UserValidator userValidator;
	private UserService userService;
	
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	@RequestMapping("/login")
	public String loginAndReg(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model) {
		if(error!=null) {
			model.addAttribute("errorMessage", "Invalid credentials, please try again");
		}
		if(logout!=null) {
			model.addAttribute("logoutMessage", "Logout successful!!");
		}
		return "loginPage";
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "loginPage";
		}
		
		userService.saveUser(user);
		return "redirect:/login";
	}
	
	@RequestMapping(value = {"/", "/bright_ideas"})
	public String home(Principal principal, Model model, @Valid @ModelAttribute("idea") Idea idea, @Valid @ModelAttribute("user") User user) {
		String email = principal.getName();
		model.addAttribute("currentUser", userService.findByEmail(email));
		model.addAttribute("ideas", userService.allIdeas());
		model.addAttribute("users", userService.allUsers());
		return "home";
	}
	
	@RequestMapping("/users/{id}")
	public String specificUser(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.findOneUser(id));
		return "userDetail";
	}
	
	@PostMapping("/addidea")
	public String addIdea(@ModelAttribute("idea") Idea idea, BindingResult result, Principal principal) {
		String email = principal.getName();
		
		userService.createIdea(idea);
		
		return "redirect:/bright_ideas";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id) {
		userService.delete(id);
		return "redirect:/bright_ideas";
	}
	
	@RequestMapping("/bright_ideas/{id}")
	public String specificIdea(@PathVariable("id") Long id, Model model, @ModelAttribute("idea") Idea idea, Principal principal) {
		String email = principal.getName();
		model.addAttribute("idea", userService.findOneIdea(id));
		model.addAttribute("currentUser", userService.findByEmail(email));
		return "oneIdea";
	}
	
	
	@RequestMapping("/like/{id}")
	public String ideaLike(@PathVariable(value="id") Long id, Principal principal) {
		userService.ideaLike(id, userService.findByEmail(principal.getName()).getId());
		return "redirect:/bright_ideas";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
}
