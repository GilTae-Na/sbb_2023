package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    //questionRepository 객체는 생성자 방식으로 DI 규칙에 의해 주입된다.

    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

}
