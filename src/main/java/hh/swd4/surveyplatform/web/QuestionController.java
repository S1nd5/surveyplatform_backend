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

import hh.swd4.surveyplatform.domain.AnswerRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/questions", produces = "application/json")
public class QuestionController {
	
	private final QuestionRepository questionRepository;
	
	QuestionController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
		this.questionRepository = questionRepository;
	}
	
	@GetMapping
	List<Question> allQuestions() {
		return questionRepository.findAll();
	}
	
	@GetMapping(value="/{id}")
	//ResponseEntity<Question
	public @ResponseBody Optional<Question> findById(@PathVariable("id") Long questionId) {
		/*Optional<Question> q = this.questionRepository.findById(questionId);
		if ( q.isPresent()) {
			Question responseQuestion = q.get();
			responseQuestion.add(linkTo(methodOn(QuestionController.class).responseQuestion.getQ_id()).withSelfRel());
			return new ResponseEntity<Question>(responseQuestion, HttpStatus.OK);
		} else {
			return new ResponseEntity<Question>(new Question(), HttpStatus.NOT_FOUND);
		}*/
		return this.questionRepository.findById(questionId);
	}
	
	@DeleteMapping(value="/{id}")
	
	public ResponseEntity<String> deleteById(@PathVariable("id") Long q_id) {
		
		if(!questionRepository.existsById(q_id)) {
			return new ResponseEntity<String>("Failed, invalid request body", HttpStatus.NOT_FOUND);
		}
		
		questionRepository.deleteById(q_id);
		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Question> insert(@RequestBody Question question) throws JsonMappingException, JsonProcessingException {
		if(question.getQuestion() == null) {
			return new ResponseEntity<Question>( HttpStatus.BAD_REQUEST);
		}else {
			questionRepository.save(question);
			return new ResponseEntity<Question>(question, HttpStatus.OK);
	}
	}

}
