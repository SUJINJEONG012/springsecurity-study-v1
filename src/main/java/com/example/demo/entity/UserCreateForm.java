package com.example.demo.entity;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserCreateForm {

	@Size(min=3, max=25)
	@NotEmpty(message = "사용자ID는 필수항목입니다.")
	private String username;
	
	@NotEmpty(message="비밀번호는 필수항목입니다.")
	private String password1;
	
	@NotEmpty(message="비밀번호 확인은 필수항목입니다.")
	private String password2;
	
	@NotEmpty(message="이메일은 필수항목입니다.")
	private String email;
}
