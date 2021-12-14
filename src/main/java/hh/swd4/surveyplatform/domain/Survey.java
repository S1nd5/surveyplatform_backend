package hh.swd4.surveyplatform.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@JsonDeserialize(as = Survey.class)
@JsonComponent
@JsonIgnoreProperties({"hibernateLazyInitializer", "answers", "respondents"})
public class Survey {
@JsonProperty("s_id")
private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long s_id;

private String name;
private boolean active;

@OneToMany(mappedBy = "q_id", orphanRemoval = false)
@JsonManagedReference
private List<Question> questions;

@OneToMany(mappedBy = "a_id", cascade = CascadeType.ALL, orphanRemoval = false)
@JsonIgnore
private List<Answer> answers;

@OneToMany(mappedBy = "r_id", cascade = CascadeType.ALL, orphanRemoval = false)
@JsonIgnore
private List<Respondent> respondents;

@JsonCreator
public Survey() {
	super();
}

@JsonCreator
public Survey(@JsonProperty("name") String name) {
	super();
	this.name = name;
	this.questions = new ArrayList<>();
	this.active = true;
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

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

@Override
public String toString() {
	return "Survey [s_id=" + s_id + ", name=" + name + "]";
}

}
