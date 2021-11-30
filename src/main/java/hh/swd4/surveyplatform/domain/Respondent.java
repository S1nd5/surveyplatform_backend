package hh.swd4.surveyplatform.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@JsonComponent
@JsonDeserialize(as=Respondent.class)
public class Respondent {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long r_id;

	private String firstname;
	private String lastname;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="s_id", unique=false)
	@JsonIgnore
	private Survey survey;
	
	public Respondent() {}
	
	public Respondent(Survey survey) {
		super();
		this.survey = survey;
	}
	
	public Respondent(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Respondent(String firstname, String lastname, Survey survey) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.survey = survey;
	}

	public Long getR_id() {
		return r_id;
	}

	public void setR_id(Long r_id) {
		this.r_id = r_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
}
