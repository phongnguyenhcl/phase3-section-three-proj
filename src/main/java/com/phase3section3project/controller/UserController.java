package com.phase3section3project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phase3section3project.model.User;
import com.phase3section3project.service.UserService;

/**
 * 
 * @author Phong Van Nguyen
 *
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * direct to the homepage of the application
	 * @return the homepage view
	 */
	@RequestMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	/**
	 * retrieve all current users and direct to the list of users page
	 * @param model 
	 * @return list of users view
	 */
	@RequestMapping("/users")
	public String viewListOfUserHomePage(Model model) {
		List<User> listOfAllUser = userService.listAll();
		model.addAttribute("listOfUsers", listOfAllUser);
		
		return "users";
	}
	
	/**
	 * direct to the registration form page
	 * @param model
	 * @return registration page view
	 */
	@RequestMapping("/register-user")
	public String registerUser(Model model) {
		User newUser = new User();
		model.addAttribute("newUser", newUser);
		
		return "register_user";
	}
	
	/**
	 * save new user to the database and direct to success page
	 * @param newUser
	 * @return register_success page view
	 */
	@PostMapping("/save-user")
	public String saveUser(@ModelAttribute("newUser") User newUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		newUser.setPassword(encoder.encode(newUser.getPassword()));
		userService.saveUser(newUser);
		
		return "register_success";
	}
	
}
