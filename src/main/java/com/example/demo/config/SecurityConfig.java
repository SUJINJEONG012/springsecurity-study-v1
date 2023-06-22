package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

/*
 * @Configuration은 스프링의 환경설정 파일
 * @EnableWebSecurity는 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private static final String[] AUTH_WHITELIST = {
            "/",  "/user/**", "/question/**"       
    };
	
	@Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITELIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .build();
    }
	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
}
