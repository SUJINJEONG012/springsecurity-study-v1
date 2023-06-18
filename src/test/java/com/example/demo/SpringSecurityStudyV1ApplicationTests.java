package com.example.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;

@SpringBootTest
class SpringSecurityStudyV1ApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void contextLoads() {
		Question q1 = new Question();
		q1.setSubject("스프링부트가뭔가요 ?");
		q1.setContent("스프링 부트와 스프링의차이점을 차이점을 알기 위해 학습중입니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("개발이 재밌지만 잘 할 수 있을까요?");
		q2.setContent("저는 스프링부트를 잘다룰수 있을까요 ?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
		
		
	}

}
