package hh.swd4.surveyplatform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Survey {

 
private @Id @GeneratedValue Long s_id;
private String name;

public Survey() {
	super();
}

public Survey(String name) {
	super();
	this.name = name;
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

@Override
public String toString() {
	return "Survey [s_id=" + s_id + ", name=" + name + "]";
}











}
