package hh.swd4.surveyplatform.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository <Question, Long> {
	Optional<Question> findById(Long id);
	List<Question> findAllBySurvey(Survey survey);
	List<Question> findAll();
}
