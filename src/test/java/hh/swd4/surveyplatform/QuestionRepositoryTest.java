package hh.swd4.surveyplatform;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest
public class QuestionRepositoryTest {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Test
	public void findQuestion() {
		Survey newSurvey = new Survey("Testikysely 1");
		Question newQuestion = new Question(newSurvey, "Myöhästyikö Otso tänään junasta?", "Kyllä", "Mahollisesti", "Ei", "Ehkä");
		Question newQuestion2 = new Question(newSurvey, "Mitä oli tänään ruokana koulussa?", "Seitaa", "Jauhelihamakaronia", "Kasvis vegepaska", "Keittolounas");
		
		surveyRepository.save(newSurvey);
		questionRepository.save(newQuestion);
		questionRepository.save(newQuestion2);
		
		List <Survey> surveys = surveyRepository.findAll();
		Survey survey = surveys.get(0);
		List <Question> questions = survey.getQuestions();
		
		assertThat(questions.get(0).getQuestion().equals("Myöhästyikö Otso tänään junasta?"));
		assertThat(questions).hasSize(2);
	}
	

}
