package hh.swd4.surveyplatform.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import hh.swd4.surveyplatform.domain.Option;
import hh.swd4.surveyplatform.domain.OptionRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/surveys", produces = "application/json")
public class SurveyController {

	private final SurveyRepository surveyRepository;
	private final QuestionRepository questionRepository;
	private final OptionRepository optionRepository;
	
	SurveyController(SurveyRepository surveyRepository, QuestionRepository questionRepository, OptionRepository optionRepository) {
		this.surveyRepository = surveyRepository;
		this.questionRepository = questionRepository;
		this.optionRepository = optionRepository;
	}
	
	@GetMapping
	List<Survey> allSurveys() {
		List<Survey> surveys = this.surveyRepository.findAll();
		for (Survey s : surveys) {
			List<Question> questions = this.questionRepository.findAllBySurvey(s);
			//Options
			for (Question q : questions) {
				List<Option> options = this.optionRepository.findAllByQuestion(q);
				q.setOptions(options);
			}
			s.setQuestions(questions);
		}
		return surveys;
	}
	
	@GetMapping(value="/{id}")
	public @ResponseBody ResponseEntity<Survey> findById(@PathVariable("id") Long surveyId) {
		Optional<Survey> thisSurvey = this.surveyRepository.findById(surveyId);
		if ( thisSurvey.isPresent()) {
			Survey survey = thisSurvey.get();
			List<Question> questions = this.questionRepository.findAllBySurvey(survey);
			//Options
			for (Question q : questions) {
				List<Option> options = this.optionRepository.findAllByQuestion(q);
				q.setOptions(options);
			}
			survey.setQuestions(questions);
			return new ResponseEntity<Survey>(survey, HttpStatus.OK);
		} else {
			return new ResponseEntity<Survey>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long s_id) {
		
		if(!surveyRepository.existsById(s_id)) {
			return new ResponseEntity<String>("Failed, invalid request body", HttpStatus.NOT_FOUND);
		}
		
		surveyRepository.deleteById(s_id);		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Survey> insert(@RequestBody Survey survey) throws JsonMappingException, JsonProcessingException {
		if(survey.getName() == null) {
			return new ResponseEntity<Survey>( HttpStatus.BAD_REQUEST);
		} else {
			surveyRepository.save(survey);
			return new ResponseEntity<Survey>(survey, HttpStatus.OK);
		}
	}
}
