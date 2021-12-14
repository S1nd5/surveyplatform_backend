package hh.swd4.surveyplatform.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "o_id")
public class Option {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long o_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="q_id")
	@JsonBackReference
	private Question question;
	
	private String option;
	
	public Option() {
		super();
	}

	public Option(Question question, String option) {
		super();
		this.question = question;
		this.option = option;
	}

	public Long getO_id() {
		return o_id;
	}

	public void setO_id(Long o_id) {
		this.o_id = o_id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
}
