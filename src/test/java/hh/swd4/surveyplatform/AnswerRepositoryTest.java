package hh.swd4.surveyplatform;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import hh.swd4.surveyplatform.domain.Answer;
import hh.swd4.surveyplatform.domain.AnswerRepository;
import hh.swd4.surveyplatform.domain.Option;
import hh.swd4.surveyplatform.domain.OptionRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.QuestionType;
import hh.swd4.surveyplatform.domain.QuestionTypeRepository;
import hh.swd4.surveyplatform.domain.Respondent;
import hh.swd4.surveyplatform.domain.RespondentRepository;
import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class AnswerRepositoryTest {
	
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private QuestionTypeRepository questionTypeRepository;

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private OptionRepository optionRepository;
	
	@Autowired
	private RespondentRepository respondentRepository;

	@Autowired
	private AnswerRepository answerRepository;
	
	@Test
	public void createAnswerForQuestion() {
		
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
		
		Respondent respondent = new Respondent("Testi", "Teppo");
		
		surveyRepository.save(newSurvey);
		questionRepository.save(newQuestion);
		questionRepository.save(newQuestion2);
		respondentRepository.save(respondent);
		
		Answer uusVastaus = new Answer(newQuestion, newSurvey, respondent, "Kyllä");
		answerRepository.save(uusVastaus);
		
		Answer vastaus = answerRepository.findById((long) 1);
		
		assertThat(vastaus.getAnswer1().equals("Kyllä"));
		assertThat(vastaus).isNotNull();
	}
}
