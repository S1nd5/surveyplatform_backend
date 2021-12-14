package hh.swd4.surveyplatform.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "q_id")
public class Question {

private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long q_id;

@ManyToOne
@JoinColumn(name="s_id")
@JsonIgnore
private Survey survey;

@OneToMany(fetch = FetchType.EAGER, mappedBy = "a_id", cascade = CascadeType.ALL)
@JsonIgnore
private List<Answer> answers;

private String question;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="qt_id")
@JsonManagedReference
private QuestionType q_type;

@OneToMany(fetch = FetchType.EAGER, mappedBy = "o_id", cascade = CascadeType.ALL)
@JsonManagedReference
private List<Option> options;

public Question() {
	super();
}

public Question(Survey survey, String question, QuestionType q_type) {
	super();
	this.survey = survey;
	this.question = question;
	this.q_type = q_type;
	this.options = new ArrayList<Option>();
}

@JsonCreator
public Question(Survey survey, String question, List<Option> opt, QuestionType q_type) {
	super();
	this.survey = survey;
	this.question = question;
	this.q_type = q_type;
	this.options = opt;
}

public QuestionType getQ_type() {
	return q_type;
}

public void setQ_type(QuestionType q_type) {
	this.q_type = q_type;
}

public Long getQ_id() {
	return q_id;
}

public String getQuestion() {
	return question;
}

public List<Option> getOptions() {
	return options;
}

public void setQ_id(Long q_id) {
	this.q_id = q_id;
}

public void setQuestion(String question) {
	this.question = question;
}

public void setOptions(List<Option> opt) {
	this.options = opt;
}

public Survey getSurvey() {
	return survey;
}

public void setSurvey(Survey survey) {
	this.survey = survey;
}

@Override
public String toString() {
	return "Question [q_id=" + q_id + ", survey=" + survey + ", answers=" + answers + ", question=" + question
			+ ", q_type=" + q_type + ", options=" + options + "]";
}

}
