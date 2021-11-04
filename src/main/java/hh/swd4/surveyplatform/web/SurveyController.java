package hh.swd4.surveyplatform.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping(value="/survey/{id}")
	public @ResponseBody Optional<Survey> findById(@PathVariable("id") Long surveyId) {
		return this.surveyRepository.findById(surveyId);
	}
	
	@PostMapping(value="/answer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Answer> insert(@RequestBody Answer answer) {

	    /*if (answer != null) {
	        answer.setSurvey(surveyRepository.findById(answer.getSurvey().getS_id()));
	    }*/
		answerRepository.save(answer);

	    // TODO: call persistence layer to update
	    return new ResponseEntity<Answer>(answer, HttpStatus.OK);
	}
	//Answer newAnswer(@RequestBody Answer newAnswer) throws JsonMappingException, JsonProcessingException {
		//Survey SurveyEntity = surveyRepository.findById(newAnswer.getQuestion());
		//return answerRepository.save(newAnswer);
	
}
