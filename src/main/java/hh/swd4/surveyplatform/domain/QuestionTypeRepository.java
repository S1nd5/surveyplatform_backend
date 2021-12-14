package hh.swd4.surveyplatform.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface QuestionTypeRepository extends CrudRepository <QuestionType, Long> {
	
	Optional<QuestionType> findById(Long id);
	
	List<QuestionType> findAllByName(String name);
	
	List<QuestionType> findAll();
}
