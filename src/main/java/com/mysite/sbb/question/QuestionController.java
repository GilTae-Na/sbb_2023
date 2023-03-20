package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/question") //URL 프리픽스(prefix), 공통 url 위로 뺴놓기, 필수는 아님
@RequiredArgsConstructor //questionRepository 포함하는 생성자 생성, final이 붙은 속성을 포함하는 생성자를 자동으로 생성
@Controller
public class QuestionController {

    private final QuestionService questionService;
    //questionService 객체는 생성자 방식으로 DI 규칙에 의해 주입된다.

    @GetMapping("/list")
    public String list(Model model){

        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute(question);
        return "question_detail";
    }
}
