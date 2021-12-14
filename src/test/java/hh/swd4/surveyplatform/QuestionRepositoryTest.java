package hh.swd4.surveyplatform;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd4.surveyplatform.domain.Option;
import hh.swd4.surveyplatform.domain.OptionRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.QuestionType;
import hh.swd4.surveyplatform.domain.QuestionTypeRepository;
import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class QuestionRepositoryTest {
	
	@Autowired
	private QuestionTypeRepository questionTypeRepository;

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private OptionRepository optionRepository;
	
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Test
	public void findQuestion() {
		
		ArrayList<Option> options1 = new ArrayList<>();
		ArrayList<Option> options2 = new ArrayList<>();
		
		Survey newSurvey = new Survey("Testikysely 1");
		QuestionType qt = new QuestionType("radio");
		
		surveyRepository.save(newSurvey);
		questionTypeRepository.save(qt);
		
		Question newQuestion = new Question(newSurvey, "Myöhästyikö Otso tänään junasta?", qt);
		Question newQuestion2 = new Question(newSurvey, "Mitä oli tänään ruokana koulussa?", qt);
		
		options1.add(optionRepository.save(new Option(newQuestion, "Kyllä")));
		options1.add(optionRepository.save(new Option(newQuestion, "Ei")));
		options1.add(optionRepository.save(new Option(newQuestion, "Ehkä")));
		
		newQuestion.setOptions(options1);
		questionRepository.save(newQuestion);
		
		options2.add(optionRepository.save(new Option(newQuestion2, "Seitaa")));
		options2.add(optionRepository.save(new Option(newQuestion2, "Jauhelihamakaronia")));
		options2.add(optionRepository.save(new Option(newQuestion2, "Kasvis vegepaska")));
		options2.add(optionRepository.save(new Option(newQuestion2, "Keittolounas")));
		
		newQuestion.setOptions(options2);
		questionRepository.save(newQuestion2);
		
		List <Survey> surveys = surveyRepository.findAll();
		
		Survey survey = surveys.get(0);
		List <Question> questions = questionRepository.findAllBySurvey(survey);
		
		assertThat(questions.get(0).getQuestion().equals("Myöhästyikö Otso tänään junasta?"));
		assertThat(questions).hasSize(2);
	}
}
