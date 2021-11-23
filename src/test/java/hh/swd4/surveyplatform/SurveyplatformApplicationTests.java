package hh.swd4.surveyplatform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import hh.swd4.surveyplatform.web.AnswerController;

@SpringBootTest
class SurveyplatformApplicationTests {
	
	@Autowired
	private AnswerController aController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(aController).getClass();
	}

}
