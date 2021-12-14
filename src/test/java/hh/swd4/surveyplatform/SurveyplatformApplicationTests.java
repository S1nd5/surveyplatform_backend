package hh.swd4.surveyplatform;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

import hh.swd4.surveyplatform.web.SurveyController;
import hh.swd4.surveyplatform.web.QuestionController;
import hh.swd4.surveyplatform.web.AnswerController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class SurveyplatformApplicationTests {
	
	@Autowired
	private SurveyController sController;
	
	@Autowired
	private QuestionController qController;
	
	@Autowired
	private AnswerController aController;
	
	/*@Test
	public void contextLoads() throws Exception {
		/*assertThat(sController.getClass());
		assertThat(qController.getClass());
		assertThat(aController.getClass());
	}*/

}
