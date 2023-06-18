package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Question;

public interface QuestionRepository  extends JpaRepository<Question, Integer>{

	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String Content);
	List<Question> findBySubjectLike(String subject);
}
