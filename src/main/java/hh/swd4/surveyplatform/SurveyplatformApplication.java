package hh.swd4.surveyplatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd4.surveyplatform.domain.Question;
import hh.swd4.surveyplatform.domain.QuestionRepository;



@SpringBootApplication
public class SurveyplatformApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SurveyplatformApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SurveyplatformApplication.class, args);
	}

	@Bean
	public CommandLineRunner questionnaireDemo (QuestionRepository qrepository) {
		return (args) -> {
			log.info("save a couple of students");
			qrepository.save(new Question("Osaako virtahevot uida?", "Kyllä", "Khyä", "Ei"));
		
			
			

		};
			
		}
	}
	

