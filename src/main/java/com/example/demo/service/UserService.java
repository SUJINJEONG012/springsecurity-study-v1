package com.example.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SiteUser;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository; 
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		//user.setPassword(password);
		
		/* 비밀번호는 인코딩 
		 * 
		 * BCryptPasswordEncoder는 BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 암호화한다.
		 * 
		 * */
		
		BCryptPasswordEncoder passowrdEncoder = new BCryptPasswordEncoder();
		user.setPassword(passowrdEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
}
