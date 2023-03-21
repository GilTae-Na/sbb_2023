package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    //QuestionForm과 같이 매개변수로 바인딩한 객체는
    // Model 객체로 전달하지 않아도 템플릿에서 사용이 가능하다.

    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max=200)
    private String subject;

    @NotEmpty(message="내용은 필수항목 입니다.")
    private String content;
}
