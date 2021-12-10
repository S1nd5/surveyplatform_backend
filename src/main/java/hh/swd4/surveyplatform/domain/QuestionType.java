package hh.swd4.surveyplatform.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class QuestionType {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long qt_id;
	
	@ManyToOne
	@JoinColumn(name="q_id")
	@JsonBackReference("question")
	private Question question;
	
	private String name;
	
	public QuestionType(Long qt_id, Question question, String name) {
		super();
		this.qt_id = qt_id;
		this.question = question;
		this.name = name;
	}
}
