package hh.swd4.surveyplatform.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hh.swd4.surveyplatform.domain.Answer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping(value = "/answers", produces = "application/hal+json")
public class AnswerController {
	private final AnswerRepository answerRepository;
	private final SurveyRepository surveyRepository;
	private final QuestionRepository questionRepository;
	
	AnswerController(AnswerRepository answerRepository, SurveyRepository surveyRepository, QuestionRepository questionRepository) {
		this.answerRepository = answerRepository;
		this.surveyRepository = surveyRepository;
		this.questionRepository = questionRepository;
	}
	
	@GetMapping
	List<Answer> allQuestions() {
		return answerRepository.findAll();
	}
	
	@GetMapping(value="/{id}")
	public @ResponseBody Optional<Answer> findById(@PathVariable("id") Long questionId) {
		return this.answerRepository.findById(questionId);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> insert(@RequestBody String answer) throws JsonMappingException, JsonProcessingException {
		
		// Getting values from the JSON body
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String,Object> map = mapper.readValue(answer, Map.class);
			
			// Defining values from mapped JSON to Java variables
			
			Long surveyId = Long.valueOf((String) map.get("survey"));
			Long questionId = Long.valueOf((String) map.get("question"));
			
			// Defining answers
			String answer1 =  map.getOrDefault("answer1", "").toString();
			String answer2 = map.getOrDefault("answer2", "").toString();
			String answer3 = map.getOrDefault("answer3", "").toString();
			String answer4 = map.getOrDefault("answer4", "").toString();
			
			// Get Entities By Id
			
			Optional<Survey> s = surveyRepository.findById(surveyId);
			Optional<Question> q = questionRepository.findById(questionId);
			
			// Check that the entities are really there
			
			if ( s.isPresent() && q.isPresent() && answer1.length() > 1 ) {
				Survey exSurvey = s.get();
				Question exQuestion = q.get();
				Answer a = new Answer();
				a.setSurvey(exSurvey);
				a.setQuestion(exQuestion);
				a.setAnswer1(answer1);
				a.setAnswer2(answer2);
				a.setAnswer3(answer3);
				a.setAnswer4(answer4);
				answerRepository.save(a);
			    return new ResponseEntity<String>(a.toString(), HttpStatus.OK);
			    
			} else {
				return new ResponseEntity<String>("Failed, invalid request body", HttpStatus.EXPECTATION_FAILED);
				
			}
		} catch (Exception e ) {
			
			return new ResponseEntity<String>("Failed, invalid request body", HttpStatus.EXPECTATION_FAILED);
		}
	}
}
