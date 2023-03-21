package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question") //URL 프리픽스(prefix), 공통 url 위로 뺴놓기, 필수는 아님
@RequiredArgsConstructor //생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션.
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
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute(question);
        return "question_detail";
    }

    @GetMapping(value = "/create")
    public String questionCreate(QuestionForm questionForm){
        //QuestionForm과 같이 매개변수로 바인딩한 객체는
        // Model 객체로 전달하지 않아도 템플릿에서 사용이 가능하다.
        return  "question_form";
    }

    @PostMapping(value = "/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return  "redirect:/question/list";
    }
    //subject, content 항목을 지닌 폼이 전송되면
    //QuestionForm의 subject, content 속성이 자동으로 바인딩
    //BindingResult는 @Valid로 인해 검증이 수행된 결과를 의미하는 객체
    // @Valid : QuestionForm의 @NotEmpty, @Size 등으로 설정한 검증 기능이 동작한다.
    //BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치, 아니면 오류남


}
