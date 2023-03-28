package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionService questionService;

	/*@BeforeEach
		// 아래 메서드는 각 테스트케이스가 실행되기 전에 실행된다.
	void beforeEach() {
		// 모든 데이터 삭제
		answerRepository.deleteAll();
		answerRepository.clearAutoIncrement();

		// 모든 데이터 삭제
		questionRepository.deleteAll();

		// 흔적삭제(다음번 INSERT 때 id가 1번으로 설정되도록)
		questionRepository.clearAutoIncrement();

		// 질문 1개 생성
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);  // 첫번째 질문 저장

		// 질문 1개 생성
		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2);  // 두번째 질문 저장

		// 답변 1개 생성
		Answer a1 = new Answer();
		a1.setContent("네 자동으로 생성됩니다.");
		q2.addAnswer(a1);
		a1.setCreateDate(LocalDateTime.now());
		answerRepository.save(a1);
	}*/

	@Transactional
	@Test
	@DisplayName("데이터 생성")
	void testJpa() {
		/*데이터 삽입
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요");
		q1.setContent("모름니당");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("뭘봐 임마");
		q2.setContent("죄송합니다");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);*/

		/*findAll
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요", q.getSubject());*/

		/*findById
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()){
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요", q.getSubject());
		}*/

		/*findBySubject
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요");
		assertEquals(1, q.getId());*/

		/*findBySubjectAndContent
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요", "모름니당");
		assertEquals(1, q.getId());*/

		/*findByLike
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요", q.getSubject());*/

		/*데이터 수정
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);*/

		/* 데이터 삭제하기
		assertEquals(2, this.questionRepository.count()); //삭제전 2개
		Optional<Question> oq = this.questionRepository.findById(1); //1번 게시물 가져오기
		assertTrue(oq.isPresent()); //null인지 아닌지 검사
		Question q = oq.get(); //q 에 가죠와서
		this.questionRepository.delete(q); //q삭제
		assertEquals(1, this.questionRepository.count()); //삭제후 1개*/



		/*답변 데이터 생성 후 저장하기
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);*/

		/*답변 조회하기
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());*/

		//답변에 연결된 질문 찾기 vs 질문에 달린 답변 찾기
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}

	@Test
	void test2(){
		for(int i=1; i<=300; i++){
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "구라지롱";
			this.questionService.create(subject, content, null);
		}
	}
}
