package hh.swd4.surveyplatform.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SurveyRepository extends CrudRepository <Survey, Long> {
	List<Survey> findAll();
}
