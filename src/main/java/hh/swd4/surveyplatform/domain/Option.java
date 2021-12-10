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
public class Option {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long o_id;
	
	@ManyToOne
	@JoinColumn(name="q_id")
	@JsonBackReference("question")
	private Question question;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="qt_id")
	private QuestionType type;
	private String option;
	
	public Option(Long o_id, Question question, QuestionType type, String option) {
		super();
		this.o_id = o_id;
		this.question = question;
		this.type = type;
		this.option = option;
	}
	
	
}
