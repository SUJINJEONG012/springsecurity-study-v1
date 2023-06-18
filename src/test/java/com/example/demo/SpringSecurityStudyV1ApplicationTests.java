package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;

@SpringBootTest
class SpringSecurityStudyV1ApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	
	
	@Transactional
	@Test
	void contextLoads() {
		//데이터생성
//		Question q1 = new Question();
//		q1.setSubject("스프링부트가뭔가요 ?");
//		q1.setContent("스프링 부트와 스프링의차이점을 차이점을 알기 위해 학습중입니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		
//		Question q2 = new Question();
//		q2.setSubject("개발이 재밌지만 잘 할 수 있을까요?");
//		q2.setContent("저는 스프링부트를 잘다룰수 있을까요 ?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
		
		//데이터 조회
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//		Question q = all.get(0);
//		assertEquals("스프링부트가뭔가요 ?",q.getSubject());
//		System.out.println(q.getSubject());
		

//		Optional<Question> oq = this.questionRepository.findById(1);
//		if(oq.isPresent()) {
//			Question q = oq.get();
//			assertEquals("스프링부트가뭔가요 ?", q.getSubject());		
//			
//		}
		
//		Question q = this.questionRepository.findBySubject("스프링부트가뭔가요 ?");
//		assertEquals(1, q.getId());
		
		
//		List<Question> qList = this.questionRepository.findBySubjectLike("스프링%");
//		Question q = qList.get(0);
//		assertEquals("스프링부트가뭔가요 ?", q.getSubject());
//		System.out.println(q.getSubject());
		
		
		/* 데이터 수정 */
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q= oq.get();
//		q.setSubject("수정된 제목");
//		this.questionRepository.save(q);
		
		/* 데이터 삭제 */
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		this.questionRepository.delete(q);
//		assertEquals(1, this.questionRepository.count());
		
		/* =============  답변 데이터  생성 후 저장 =========== */
//		Optional<Question> oq= this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		
//		Answer a = new Answer();
//		a.setContent("답변 생성 테스트 ");
//		a.setQuestion(q); //질문아이디를 알기위해서 Question객체가 필요
//		a.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(a);
		
//		Optional<Answer> oa = this.answerRepository.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a = oa.get();
//		assertEquals(2, a.getQuestion().getId());
//		System.out.println(a.getQuestion().getId());
	
		
		Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("답변 생성 테스트 ", answerList.get(0).getContent());
	}

}
