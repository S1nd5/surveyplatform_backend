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
private String opt1;
private String opt2;
private String opt3;
private String opt4;

public Question() {
	super();
}

@JsonCreator
public Question(@JsonProperty("survey") Survey survey, @JsonProperty("question") String question, @JsonProperty("opt1") String opt1, @JsonProperty("opt2") String opt2, @JsonProperty("opt3") String opt3, @JsonProperty("opt4") String opt4, @JsonProperty("q_type") String q_type) {
	super();
	this.survey = survey;
	this.question = question;
	this.q_type = q_type;
	this.opt1 = opt1;
	this.opt2 = opt2;
	this.opt3 = opt3;
	this.opt4 = opt4;
}

public Question(@JsonProperty("survey") Survey survey,@JsonProperty("question")  String question, @JsonProperty("opt1") String opt1, @JsonProperty("opt2")  String opt2, @JsonProperty("opt3") String opt3, @JsonProperty("q_type") String q_type) {
	super();
	this.survey = survey;
	this.question = question;
	this.q_type = q_type;
	this.opt1 = opt1;
	this.opt2 = opt2;
	this.opt3 = opt3;
}

public Question(@JsonProperty("survey") Survey survey,@JsonProperty("question") String question, @JsonProperty("opt1") String opt1, @JsonProperty("opt2") String opt2, @JsonProperty("q_type") String q_type) {
	super();
	this.survey = survey;
	this.question = question;
	this.q_type = q_type;
	this.opt1 = opt1;
	this.opt2 = opt2;
}

public Question(@JsonProperty("survey") Survey survey,@JsonProperty("question") String question, @JsonProperty("opt1") String opt1, @JsonProperty("q_type") String q_type) {
	super();
	this.survey = survey;
	this.question = question;
	this.q_type = q_type;
	this.opt1 = opt1;
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

public String getOpt1() {
	return opt1;
}

public String getOpt2() {
	return opt2;
}

public String getOpt3() {
	return opt3;
}

public String getOpt4() {
	return opt4;
}

public void setQ_id(Long q_id) {
	this.q_id = q_id;
}

public void setQuestion(String question) {
	this.question = question;
}

public void setOpt1(String opt1) {
	this.opt1 = opt1;
}

public void setOpt2(String opt2) {
	this.opt2 = opt2;
}

public void setOpt3(String opt3) {
	this.opt3 = opt3;
}

public void setOpt4(String opt4) {
	this.opt4 = opt4;
}

public Survey getSurvey() {
	return survey;
}

public void setSurvey(Survey survey) {
	this.survey = survey;
}

@Override
public String toString() {
	return "Question [q_id=" + q_id + ", question=" + question + ", opt1=" + opt1 + ", opt2=" + opt2 + ", opt3=" + opt3
			+ ", opt4=" + opt4 + "]";
}






}
