package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Question question;
	/*
	 * 답변은 하나의 질문에 여러개가 달릴 수 있다.
	 * 답변은 Many가 되고 질문은 하나가 된다.
	 * ManyToOne (N:1 관계)
	 * 
	 * 
	 * */
	
	@ManyToOne
	private SiteUser author;
	
	//수정한날짜 
	private LocalDateTime modifyDate;
	

}
