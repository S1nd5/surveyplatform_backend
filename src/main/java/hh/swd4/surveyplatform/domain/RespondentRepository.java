package hh.swd4.surveyplatform.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RespondentRepository extends CrudRepository <Respondent, Long> {
	
	Optional<Respondent> findById(Long id);
	
	List<Respondent> findAll();
}
