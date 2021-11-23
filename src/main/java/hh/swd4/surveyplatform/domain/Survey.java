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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@JsonDeserialize(as = Survey.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "answers", "respondents"})
public class Survey {
 
@JsonProperty("s_id")
private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long s_id;

private String name;

@OneToMany(mappedBy = "q_id", cascade = CascadeType.ALL, orphanRemoval = false)
private List<Question> questions;

@OneToMany(mappedBy = "a_id", cascade = CascadeType.ALL, orphanRemoval = false)
private List<Answer> answers;

@OneToMany(mappedBy = "r_id", cascade = CascadeType.ALL, orphanRemoval = false)
private List<Respondent> respondents;

public Survey() {
	super();
}

public Survey(String name) {
	super();
	this.name = name;
	this.questions = new ArrayList<>();
}

public Survey(String name, List<Question> questions) {
	super();
	this.name = name;
	this.questions = questions;
}

public Long getS_id() {
	return s_id;
}

public String getName() {
	return name;
}

public void setS_id(Long s_id) {
	this.s_id = s_id;
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

public List<Answer> getAnswers() {
	return answers;
}

public void setAnswers(List<Answer> answers) {
	this.answers = answers;
}

public List<Respondent> getRespondents() {
	return respondents;
}

public void setRespondents(List<Respondent> respondents) {
	this.respondents = respondents;
}

@Override
public String toString() {
	return "Survey [s_id=" + s_id + ", name=" + name + "]";
}

}
