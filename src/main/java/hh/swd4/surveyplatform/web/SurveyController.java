package hh.swd4.surveyplatform.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hh.swd4.surveyplatform.domain.Answer;
import hh.swd4.surveyplatform.domain.AnswerRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;
import net.minidev.json.JSONObject;

@RestController
public class SurveyController {

	private final SurveyRepository surveyRepository;
	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	
	SurveyController(SurveyRepository surveyRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
		this.surveyRepository = surveyRepository;
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
	}
	
	@GetMapping("/surveys")
	List<Survey> allSurveys() {
		return surveyRepository.findAll();
	}
	
	@GetMapping("/questions")
	List<Question> allQuestions() {
		return questionRepository.findAll();
	}
	
	@PostMapping("/questions")
	Survey newSurvey(@RequestBody Survey newSurvey) {
		return surveyRepository.save(newSurvey);
	}
	
	@PostMapping("/answers")
	Answer newAnswer(@RequestBody Answer newAnswer) {
		return answerRepository.save(newAnswer);		
	
	}
	
}
