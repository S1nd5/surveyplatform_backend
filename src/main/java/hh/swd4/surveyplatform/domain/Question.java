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
public class Question {

private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long q_id;

@ManyToOne
@JoinColumn(name="s_id")
@JsonBackReference("survey")
private Survey survey;

@OneToMany(mappedBy = "a_id", cascade = CascadeType.ALL)
private List<Answer> answers;

private String question;
private String q_type;
@OneToMany(mappedBy = "o_id", cascade = CascadeType.ALL)
private List<Option> options;

public Question() {
	super();
}

@JsonCreator
public Question(@JsonProperty("survey") Survey survey, @JsonProperty("question") String question, @JsonProperty("option") List<Option> opt, @JsonProperty("q_type") String q_type) {
	super();
	this.survey = survey;
	this.question = question;
	this.q_type = q_type;
	this.options = opt;
}

public String getQ_type() {
	return q_type;
}

public void setQ_type(String q_type) {
	this.q_type = q_type;
}

public Long getQ_id() {
	return q_id;
}

public String getQuestion() {
	return question;
}

public List<Option> getOpt() {
	return options;
}

public void setQ_id(Long q_id) {
	this.q_id = q_id;
}

public void setQuestion(String question) {
	this.question = question;
}

public void setOpt(List<Option> opt) {
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
