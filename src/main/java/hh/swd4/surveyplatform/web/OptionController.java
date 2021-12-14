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
import hh.swd4.surveyplatform.domain.QuestionRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/options", produces = "application/json")
public class OptionController {
	
	private final QuestionRepository questionRepository;
	private final OptionRepository optionRepository;
	
	OptionController(QuestionRepository questionRepository, OptionRepository optionRepository) {
		this.questionRepository = questionRepository;
		this.optionRepository = optionRepository;
	}
	
	@GetMapping
	List<Option> allOptions() {
		return this.optionRepository.findAll();
	}
	
	@GetMapping(value="/{id}")
	public @ResponseBody ResponseEntity<Option> findById(@PathVariable("id") Long optionId) {
		Optional<Option> thisOption = this.optionRepository.findById(optionId);
		if ( thisOption.isPresent()) {
			Option option = thisOption.get();
			return new ResponseEntity<Option>(option, HttpStatus.OK);
		} else {
			return new ResponseEntity<Option>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long q_id) {
		if(!optionRepository.existsById(q_id)) {
			return new ResponseEntity<String>("Failed, invalid request body", HttpStatus.NOT_FOUND);
		}
		optionRepository.deleteById(q_id);
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Option> insert(@RequestBody Option option) throws JsonMappingException, JsonProcessingException {
		if(option.getOption() == null) {
			return new ResponseEntity<Option>( HttpStatus.BAD_REQUEST);
		} else {
			optionRepository.save(option);
			return new ResponseEntity<Option>(option, HttpStatus.OK);
		}
	}

}
