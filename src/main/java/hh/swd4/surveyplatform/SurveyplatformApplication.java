package hh.swd4.surveyplatform;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@SpringBootApplication
public class SurveyplatformApplication {

	private static final Logger log = LoggerFactory.getLogger(SurveyplatformApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SurveyplatformApplication.class, args);
	}

	@Bean
	public CommandLineRunner questionnaireDemo(SurveyRepository surveyRepository,
			QuestionRepository questionRepository) {
		return (args) -> {

			/*Survey newSurvey = new Survey("Testikysely 1");
			Question newQuestion = new Question(newSurvey, "Myöhästyikö Otso tänään junasta?", "Kyllä", "Mahollisesti", "Ei", "Ehkä");
			Question newQuestion2 = new Question(newSurvey, "Mitä oli tänään ruokana koulussa?", "Seitaa", "Jauhelihamakaronia", "Kasvis vegepaska", "Keittolounas");
			
			Survey newSurvey2 = new Survey("Testikysely 2");
			Question newQuestion3 = new Question(newSurvey2, "Pääseekö hissillä Haaga-Heliassa kerrokseen numero 0?", "Kyllä", "Ei", "Ehkä", "Kerrosta ei ole olemassa");
			ArrayList<Question> questions = new ArrayList<>();
			ArrayList<Question> questions2 = new ArrayList<>();
			questions.add(newQuestion);
			questions.add(newQuestion2);
			questions2.add(newQuestion3);
			newSurvey.setQuestions(questions);
			newSurvey2.setQuestions(questions2);
			surveyRepository.save(newSurvey);
			surveyRepository.save(newSurvey2);
			questionRepository.save(newQuestion);
			questionRepository.save(newQuestion2);
			questionRepository.save(newQuestion3);
			
			List<Question> qys = questionRepository.findAllBySurvey(newSurvey);
			System.out.println(qys.toString());*/
		};

	}
}
