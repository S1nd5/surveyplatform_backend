package hh.swd4.surveyplatform.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	
	List<Answer> findAll();
	Answer findById(long id);
}
