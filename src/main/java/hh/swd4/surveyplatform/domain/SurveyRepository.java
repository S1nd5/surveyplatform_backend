package hh.swd4.surveyplatform.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface SurveyRepository extends CrudRepository <Survey, Long> {
	
	Optional<Survey> findById(Long id);
	
	List<Survey> findAll();

	List<Survey> findByName(String name);
	

}
