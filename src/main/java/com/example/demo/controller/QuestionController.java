package com.example.demo.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.AnswerForm;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionForm;
import com.example.demo.entity.SiteUser;
import com.example.demo.service.QuestionService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionService questionService;
	private final UserService userService;
	
	/* 페이징 처리 추가 */
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Question> paging = this.questionService.getList(page);
		
		model.addAttribute("paging", paging);
		return "question_list";
	}
	
	@GetMapping(value="/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	/* 리스트 페이지에서 버튼클릭시 질문등록 페이지로 이동 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	/* 질문등록 버튼 클릭시 post형식으로 보내기*/	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }
	
	/* 질문 수정 
	 * 사용자와 질문의 작성자가 동일하지 않을경우 수정권한이없습니다. 오류발생처리
	 * 
	 * */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
		
		Question question = this.questionService.getQuestion(id);
		
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	

	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
			Principal principal, @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return  "question_form";
		}
		
		Question question = this.questionService.getQuestion(id);
		
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다. ");
		}
		this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());		
		//수정이 완료되면 질문 상세 화면을 다시 호출
		return String.format("redirect:/question/detail/%s", id);
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String delete(Principal principal, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.questionService.delete(question);
		return "redirect:/";
	}
	
	
	

}
