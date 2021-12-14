package hh.swd4.surveyplatform.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	
	List<Answer> findAll();
	
	List<Answer> findAllBySurvey(Survey survey);
	
	List<Answer> findAllByQuestion(Question question);
	
	List<Answer> findAllByRespondent(Respondent respondent);
	
	Answer findById(long id);
}
