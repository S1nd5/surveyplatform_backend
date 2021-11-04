package hh.swd4.surveyplatform.web;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hh.swd4.surveyplatform.domain.Answer;
import hh.swd4.surveyplatform.domain.AnswerRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;
import net.minidev.json.JSONObject;

@RestController
public class QuestionController {
	
	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	QuestionController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
	}
	
	@GetMapping(value="/question/{id}")
	public @ResponseBody Optional<Question> findById(@PathVariable("id") Long questionId) {
		return this.questionRepository.findById(questionId);
	}
	
	@GetMapping("/questions")
	List<Question> allQuestions() {
		return questionRepository.findAll();
	}
}
