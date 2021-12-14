package hh.swd4.surveyplatform.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "a_id")
public class Answer {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long a_id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="q_id")
	@JsonManagedReference
	private Question question;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="s_id")
	@JsonManagedReference
	private Survey survey;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="r_id")
	@JsonManagedReference
	private Respondent respondent;

	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	
	public Answer() {
		super();
	}
	
	public Answer(@JsonProperty("question") Question question,  @JsonProperty("survey") Survey survey, @JsonProperty("respondent") Respondent respondent,  @JsonProperty("answer1") String answer1) {
		super();
		this.question = question;
		this.survey = survey;
		this.respondent = respondent;
		this.answer1 = answer1;
	}
	
	public Answer(Question question, Survey survey, Respondent respondent, String answer1, String answer2) {
		super();
		this.question = question;
		this.survey = survey;
		this.respondent = respondent;
		this.answer1 = answer1;
		this.answer2 = answer2;
	}
	
	public Answer(Question question, Survey survey, Respondent respondent, String answer1, String answer2, String answer3) {
		super();
		this.question = question;
		this.survey = survey;
		this.respondent = respondent;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
	}
	
	public Answer(Question question, Survey survey, Respondent respondent, String answer1, String answer2, String answer3, String answer4) {
		super();
		this.question = question;
		this.survey = survey;
		this.respondent = respondent;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
	}
	
	public Answer(Question question, Survey survey, Respondent respondent, String answer1, String answer2,
			String answer3, String answer4, String answer5) {
		super();
		this.question = question;
		this.survey = survey;
		this.respondent = respondent;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer5 = answer5;
	}

	public Respondent getRespondent() {
		return respondent;
	}

	public void setRespondent(Respondent respondent) {
		this.respondent = respondent;
	}

	public Long getA_id() {
		return a_id;
	}

	public Question getQuestion() {
		return question;
	}

	public Survey getSurvey() {
		return survey;
	}

	public String getAnswer1() {
		return answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public String getAnswer4() {
		return answer4;
	}
	
	public String getAnswer5() {
		return answer5;
	}

	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	public void setA_id(Long a_id) {
		this.a_id = a_id;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	@Override
	public String toString() {
		return "Answer [a_id=" + a_id + ", question=" + question + ", survey=" + survey + ", answer1=" + answer1
				+ ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + "]";
	}
}
