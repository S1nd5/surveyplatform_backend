package hh.swd4.surveyplatform;

import java.util.ArrayList;

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
	public CommandLineRunner questionnaireDemo (SurveyRepository surveyRepository, QuestionRepository questionRepository) {
		return (args) -> {
			log.info("save a couple of students");
			Survey newSurvey = new Survey("Testikysely");
			Question newQuestion = new Question(newSurvey, "Osaako virtahevot uida?", "Kyllä", "Khyä", "Ei", "Ehkä");
			ArrayList<Question> questions = new ArrayList<>();
			questions.add(newQuestion);
			newSurvey.setQuestions(questions);
			surveyRepository.save(newSurvey);
			questionRepository.save(newQuestion);
			System.out.println("Kysymykset");
			System.out.println(newSurvey.getQuestions());
		};
			
		}
	}
	

