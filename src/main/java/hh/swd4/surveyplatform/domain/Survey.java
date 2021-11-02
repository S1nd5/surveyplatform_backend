package hh.swd4.surveyplatform.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Survey {
 
private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long s_id;

private String name;

@OneToMany(mappedBy = "q_id", cascade = CascadeType.ALL)
@JsonBackReference
private List<Question> questions = new ArrayList<>();

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

@Override
public String toString() {
	return "Survey [s_id=" + s_id + ", name=" + name + "]";
}











}
