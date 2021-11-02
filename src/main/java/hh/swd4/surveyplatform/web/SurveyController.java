package hh.swd4.surveyplatform.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@RestController
public class SurveyController {

	private final SurveyRepository repository;
	
	SurveyController(SurveyRepository repository) {
		this.repository = repository;
	}
	
	
	@GetMapping("/questions")
	List<Survey> all() {
		return repository.findAll();
	}
	
	@PostMapping("/questions")
	Survey newSurvey(@RequestBody Survey newSurvey) {
		return repository.save(newSurvey);
	}
	
	
	
}
