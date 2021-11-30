package hh.swd4.surveyplatform;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd4.surveyplatform.domain.Survey;
import hh.swd4.surveyplatform.domain.SurveyRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest
public class SurveyRepositoryTest {

	@Autowired
	private SurveyRepository surveyRepository;
	
	
	@Test
	public void findSurvey() {
		
		Survey newSurvey = new Survey("Testikysely 1");
		
		surveyRepository.save(newSurvey);
		
		List<Survey> survey = surveyRepository.findByName("Testikysely 1");
		
		assertThat(survey.get(0).getName()).isEqualTo("Testikysely 1");
		assertThat(survey).hasSize(1);
		
		
	}
	
	
	
}
