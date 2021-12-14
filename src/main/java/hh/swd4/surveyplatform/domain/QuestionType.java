package hh.swd4.surveyplatform.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "qt_id")
public class QuestionType {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long qt_id;
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "q_id")
	@JsonIgnore
	private List<Question> questions;
	
	public QuestionType() {
		super();
	}
	
	public QuestionType(Long qt_id, String name) {
		super();
		this.qt_id = qt_id;
		this.name = name;
	}

	public QuestionType(String name) {
		super();
		this.name = name;
	}

	public Long getQt_id() {
		return qt_id;
	}

	public void setQt_id(Long qt_id) {
		this.qt_id = qt_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
