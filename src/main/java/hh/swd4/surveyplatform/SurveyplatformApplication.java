package hh.swd4.surveyplatform;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd4.surveyplatform.domain.Option;
import hh.swd4.surveyplatform.domain.OptionRepository;
import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;
import hh.swd4.surveyplatform.domain.QuestionType;
import hh.swd4.surveyplatform.domain.QuestionTypeRepository;
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
			QuestionRepository questionRepository, QuestionTypeRepository questionTypeRepository, OptionRepository optionRepository) {
		return (args) -> {

			// New Survey and Question Type
			
			Survey newSurvey = new Survey("Testikysely 1");
			QuestionType qt = new QuestionType("radio");
			QuestionType fullnameQuestion = new QuestionType("fullname");
			
			surveyRepository.save(newSurvey);
			questionTypeRepository.save(qt);
			questionTypeRepository.save(fullnameQuestion);
			
			// Lists for options
			
			ArrayList<Option> options1 = new ArrayList<>();
			ArrayList<Option> options2 = new ArrayList<>();
			ArrayList<Option> options3 = new ArrayList<>();
			ArrayList<Option> options4 = new ArrayList<>();
			
			// New Question
			
			Question newQuestion = new Question(newSurvey, "Myöhästyikö Otso tänään junasta?", qt);
			questionRepository.save(newQuestion);
			
			options1.add(optionRepository.save(new Option(newQuestion, "Kyllä")));
			options1.add(optionRepository.save(new Option(newQuestion, "Ei")));
			options1.add(optionRepository.save(new Option(newQuestion, "Ehkä")));
			
			newQuestion.setOptions(options1);
			questionRepository.save(newQuestion);
			
			// New Question
			
			Question newQuestion2 = new Question(newSurvey, "Mitä oli tänään ruokana koulussa?", qt);
			questionRepository.save(newQuestion2);
			
			options2.add(optionRepository.save(new Option(newQuestion2, "Seitaa")));
			options2.add(optionRepository.save(new Option(newQuestion2, "Jauhelihamakaronia")));
			options2.add(optionRepository.save(new Option(newQuestion2, "Kasvis vegepaska")));
			options2.add(optionRepository.save(new Option(newQuestion2, "Keittolounas")));
			
			newQuestion2.setOptions(options2);
			questionRepository.save(newQuestion2);
			
			// New Survey
			Survey newSurvey2 = new Survey("Testikysely 2");
			surveyRepository.save(newSurvey2);
			
			Question newQuestion3 = new Question(newSurvey2, "Pääseekö hissillä Haaga-Heliassa kerrokseen numero 0?", qt);
			questionRepository.save(newQuestion3);
			
			options3.add(optionRepository.save(new Option(newQuestion3, "Kyllä pääsee")));
			options3.add(optionRepository.save(new Option(newQuestion3, "Ei pääse")));
			options3.add(optionRepository.save(new Option(newQuestion3, "Ehkä pääsee")));
			options3.add(optionRepository.save(new Option(newQuestion3, "Vain poikkeustilanteissa")));
			
			newQuestion3.setOptions(options3);
			questionRepository.save(newQuestion3);
			
			Question newQuestion4 = new Question(newSurvey2, "Syötä etu- ja sukunimesi", new ArrayList<>(), fullnameQuestion);
			questionRepository.save(newQuestion4);
			options4.add(optionRepository.save(new Option(newQuestion4, "")));
			
			ArrayList<Question> questions = new ArrayList<>();
			ArrayList<Question> questions2 = new ArrayList<>();

			questions.add(newQuestion);
			questions.add(newQuestion2);
			questions2.add(newQuestion3);
			questions2.add(newQuestion4);
			newSurvey.setQuestions(questions);
			newSurvey2.setQuestions(questions2);
			
			List<Question> qys = questionRepository.findAllBySurvey(newSurvey);
			System.out.println(qys.toString());
		};

	}
}
