package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	public List<Question> getList() {
		return this.questionRepository.findAll();
	}

	/* 질문 가져오기 */
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("!!!! question not found !!!!!");
		}
	}

	public void create(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q);
	}
	
	/* 페이징 처리 */
	public Page<Question> getList(int page){
		/* 게시물 역순으로 조회하기 위해*/
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		/*Pageable 객체를 생성할때 사용한 PageRequest.of(page, 10)에서 
		 * page는 조회할 페이지의 번호이고 10은 한 페이지에 보여줄 게시물의 갯수를 의미
		 * 해당 페이지의 데이터만 조회하도록 쿼리가 변경
		 * */
		Pageable pageable = PageRequest.of(page,10);
		return this.questionRepository.findAll(pageable);
	}

}
