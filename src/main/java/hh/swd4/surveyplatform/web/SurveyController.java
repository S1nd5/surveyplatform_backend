package hh.swd4.surveyplatform.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/surveys", produces = "application/hal+json")
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
}
