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

import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/surveys", produces = "application/json")
public class SurveyController {

	private final SurveyRepository surveyRepository;

	
	SurveyController(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}
	
	@GetMapping
	List<Survey> allSurveys() {
		return surveyRepository.findAll();
	}
	
	@GetMapping(value="/{id}")
	public @ResponseBody Optional<Survey> findById(@PathVariable("id") Long surveyId) {
		return this.surveyRepository.findById(surveyId);
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
