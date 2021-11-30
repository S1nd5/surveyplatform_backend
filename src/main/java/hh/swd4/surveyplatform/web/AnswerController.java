package hh.swd4.surveyplatform.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hh.swd4.surveyplatform.domain.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import hh.swd4.surveyplatform.domain.AnswerRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.Respondent;
import hh.swd4.surveyplatform.domain.RespondentRepository;
import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

import org.json.JSONArray;
import org.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping(value = "/answers", produces = "application/json")
public class AnswerController {
	
	private final AnswerRepository answerRepository;
	private final SurveyRepository surveyRepository;
	private final QuestionRepository questionRepository;
	private final RespondentRepository respondentRepository;
	
	AnswerController(AnswerRepository answerRepository, SurveyRepository surveyRepository, QuestionRepository questionRepository, RespondentRepository respondentRepository) {
		this.answerRepository = answerRepository;
		this.surveyRepository = surveyRepository;
		this.questionRepository = questionRepository;
		this.respondentRepository = respondentRepository;
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
	public ResponseEntity<String> insert(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		
		ArrayList<Answer> createdAnswers = new ArrayList<>();
		
		JSONObject obj = new JSONObject(json);
		if ( obj.isNull("answers")) {
			return new ResponseEntity<String>("Failed, please provide valid data", HttpStatus.BAD_REQUEST);
		}
		
		/*String respondentName = obj.getJSONObject("answers").getString("respondent");
		if ( respondentName.isEmpty()) {
			return new ResponseEntity<String>("Failed, please provide valid data (respondent name missing)", HttpStatus.BAD_REQUEST);
		}*/
		
		JSONArray arr = obj.getJSONObject("answers").getJSONArray("data");
		if ( arr.isNull(0)) {
			return new ResponseEntity<String>("Failed, please provide valid data array", HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			Respondent respondent = new Respondent();
			respondentRepository.save(respondent);
			
			for ( int i = 0; i < arr.length(); i++) {
				
				JSONObject answerObj = arr.getJSONObject(i);
				
				Long s_id = answerObj.getJSONObject("survey").getLong("s_id");
				Long q_id = answerObj.getJSONObject("question").getLong("q_id");
				
				Optional<Survey> s = surveyRepository.findById(s_id);
				Optional<Question> q = questionRepository.findById(q_id);
				
				if ( s.isPresent() && q.isPresent() ) {
					
					Survey thisSurvey = s.get();
					Question thisQuestion = q.get();
					
					/* Poikkeus: tsekkaa jos kysymystyyppi on nimi ja kerää tiedot talteen */
					
					if ( thisQuestion.getQ_type().equals("fullname")) {
						if ( answerObj.getString("answer1").isEmpty() != true) {
							String[] name = answerObj.getString("answer1").split(" ");
							respondent.setFirstname(name[0]);
							respondent.setLastname(name[1]);
						}
					}
					
					String answer1 = answerObj.getString("answer1");
					String answer2 = answerObj.getString("answer2");
					String answer3 = answerObj.getString("answer3");
					String answer4 = answerObj.getString("answer4");
					
					Answer newAnswer = new Answer(thisQuestion, thisSurvey, respondent, answer1, answer2, answer3, answer4);
					respondent.setSurvey(thisSurvey);
					
					respondentRepository.save(respondent);
					answerRepository.save(newAnswer);
					createdAnswers.add(newAnswer);
				}
			}
			respondentRepository.save(respondent);
			return new ResponseEntity<String>(createdAnswers.toString(), HttpStatus.OK);
			
		} catch (Exception e ) {
			
			return new ResponseEntity<String>("Failed, exception: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
}
