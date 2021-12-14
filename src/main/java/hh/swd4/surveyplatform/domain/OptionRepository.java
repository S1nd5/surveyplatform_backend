package hh.swd4.surveyplatform.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository <Option, Long> {
	
	Optional<Option> findById(Long id);
	
	List<Option> findAllByQuestion(Question question);
	
	List<Option> findAllByOptionLike(String opt);
	
	List<Option> findAll();
}
