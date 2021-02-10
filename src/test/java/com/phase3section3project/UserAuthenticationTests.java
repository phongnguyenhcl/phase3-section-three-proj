package com.phase3section3project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.phase3section3project.model.User;
import com.phase3section3project.repository.UserRepository;
import com.phase3section3project.service.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserAuthenticationTests {
	
	@InjectMocks
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;

	@Test
	public void getUser_CheckifUserMatch() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = new User();
		user.setId(1);
		user.setUsername("teo");
		user.setPassword(encoder.encode("teopassword"));
		user.setHobby("coding");
		
		when(userRepository.findByUsername("teo")).thenReturn(user);
		
		User found = userService.findUserByName("teo");
		
		assertEquals(user, found);
		assertTrue(encoder.matches("teopassword", found.getPassword()));
	}

}
