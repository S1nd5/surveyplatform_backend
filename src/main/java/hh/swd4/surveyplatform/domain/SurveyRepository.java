package hh.swd4.surveyplatform.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import hh.swd4.surveyplatform.domain.Survey;

public interface SurveyRepository extends CrudRepository <Survey, Long> {
	
	Optional<Survey> findById(Long id);
	
	List<Survey> findAll();
}
