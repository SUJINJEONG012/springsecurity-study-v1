package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.config.DataNotFoundException;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.entity.SiteUser;
import com.example.demo.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	/* 쿼리 생성을 jpa에서 제공하는 Specification  인터페이스 를 사용하여 구현 */
	private Specification<Question> search(String kw){
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
		
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				query.distinct(true);  //중복제거
				
				Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
				Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
				Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
				
				return cb.or(cb.like(q.get("subject"), "%" + kw +"%"),
						cb.like(q.get("content"), "%" + kw + "%"),
						cb.like(u1.get("username"), "%" + kw +"%"),
						cb.like(a.get("content"), "%" + kw + "%"),   
						cb.like(u2.get("username"), "%" + kw + "%"));
			}
		};
 	}
	
	
	
	
	
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

	public void create(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
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
		Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}
	
	
	/* 질문 수정 */
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	/* 질문 삭제 */
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	
	/* 추천인 저장 */
	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}

}
