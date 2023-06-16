package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@GetMapping("/")
	
	public String index() {
		return "안녕하세요. 스프링부트 시큐리티 공부를 위한 프로젝트";
				
	}
}
