package com.phase3section3project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phase3section3project.model.User;
import com.phase3section3project.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public List<User> listAll() {
		return userRepo.findAll();
	}
	
	public void saveUser(User newUser) {
		userRepo.save(newUser);
	}
	
	public User findUserByName(String userName) {;
		return userRepo.findByUsername(userName);
	}

}
