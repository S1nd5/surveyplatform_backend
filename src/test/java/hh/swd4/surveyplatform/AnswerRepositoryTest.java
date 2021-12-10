package hh.swd4.surveyplatform;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import hh.swd4.surveyplatform.domain.Answer;
import hh.swd4.surveyplatform.domain.AnswerRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
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
	private QuestionRepository questionRepository;
	
	@Autowired
	private RespondentRepository respondentRepository;

	@Autowired
	private AnswerRepository answerRepository;
	
	@Test
	public void createAnswerForQuestion() {
		
	QuestionType qt = new QuestionType("text");
		
		Survey newSurvey = new Survey("Testikysely 1");
		Question newQuestion = new Question(newSurvey, "Myöhästyikö Otso tänään junasta?", "Kyllä", "Mahollisesti", "Ei", "Ehkä");
		Question newQuestion2 = new Question(newSurvey, "Mitä oli tänään ruokana koulussa?", "Seitaa", "Jauhelihamakaronia", "Kasvis vegepaska", "Keittolounas");
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
