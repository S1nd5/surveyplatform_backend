package hh.swd4.surveyplatform.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.Answer;
import hh.swd4.surveyplatform.domain.AnswerRepository;
import hh.swd4.surveyplatform.domain.Option;
import hh.swd4.surveyplatform.domain.OptionRepository;
import hh.swd4.surveyplatform.domain.Respondent;
import hh.swd4.surveyplatform.domain.RespondentRepository;

import org.json.JSONArray;
import org.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping(value = "/answers", produces = "application/json")
public class AnswerController {
	
	private final AnswerRepository answerRepository;
	private final SurveyRepository surveyRepository;
	private final QuestionRepository questionRepository;
	private final OptionRepository optionRepository;
	private final RespondentRepository respondentRepository;
	
	AnswerController(SurveyRepository surveyRepository, AnswerRepository answerRepository, QuestionRepository questionRepository, OptionRepository optionRepository, RespondentRepository respondentRepository) {
		this.answerRepository = answerRepository;
		this.surveyRepository = surveyRepository;
		this.questionRepository = questionRepository;
		this.optionRepository = optionRepository;
		this.respondentRepository = respondentRepository;
	}
	
	/* Saatana alkaa, poikkeusjärjestelyjä Jacksonin JsonManagedReference/JsonBackRefenrece/JsonIdentity.. ym. 
	 * Spring jäljessä olevasta versiosta johtuen. Surveyn kyssärit nollattu tarkoituksella vastauksien yhteydesä. */
	
	@GetMapping
	List<Answer> allAnswers() {
		List<Answer> answers = this.answerRepository.findAll();
		for (Answer a : answers) {
			Long surveyId = a.getSurvey().getS_id();
			Long questionId = a.getQuestion().getQ_id();
			Optional<Survey> s = surveyRepository.findById(surveyId);
			Optional<Question> q = questionRepository.findById(questionId);
			if ( s.isPresent() && q.isPresent() ) {
				Survey thisSurvey = s.get();
				Question thisQuestion = q.get();
				List<Option> options = optionRepository.findAllByQuestion(thisQuestion);
				thisQuestion.setOptions(options);
				List<Question> questions = questionRepository.findAllBySurvey(thisSurvey);
				thisSurvey.setQuestions(new ArrayList<>());
			}
		}
		return answers;
	}
	
	@GetMapping(value="/{id}")
	public @ResponseBody ResponseEntity<Answer> findById(@PathVariable("id") Long answerId) {
		Optional<Answer> a = answerRepository.findById(answerId);
		if ( a.isPresent()) {
			Answer thisAnswer = a.get();
			Long questionId = thisAnswer.getQuestion().getQ_id();
			Optional<Question> q = questionRepository.findById(questionId);
			if ( q.isPresent()) {
				Question thisQuestion = q.get();
				thisAnswer.setQuestion(thisQuestion);
				List<Option> options = optionRepository.findAllByQuestion(thisQuestion);
				thisQuestion.setOptions(options);
				thisAnswer.getSurvey().setQuestions(new ArrayList<>());
				return new ResponseEntity<Answer>(thisAnswer, HttpStatus.OK);
			} else {
				return new ResponseEntity<Answer>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Answer>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* Saatana loppuu */
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> insert(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
		
		ArrayList<Answer> createdAnswers = new ArrayList<>();
		
		JSONObject obj = new JSONObject(json);
		if ( obj.isNull("answers")) {
			return new ResponseEntity<String>("Failed, please provide valid data", HttpStatus.BAD_REQUEST);
		}
		
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
					
					if ( thisQuestion.getQ_type().getName().equals("fullname")) {
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
					String answer5 = answerObj.getString("answer5");
					
					Answer newAnswer = new Answer(thisQuestion, thisSurvey, respondent, answer1, answer2, answer3, answer4, answer5);
					respondent.setSurvey(thisSurvey);
					
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
