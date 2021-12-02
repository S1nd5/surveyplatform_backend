package hh.swd4.surveyplatform;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd4.surveyplatform.domain.Respondent;
import hh.swd4.surveyplatform.domain.RespondentRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class RespondentRepositoryTest {

	@Autowired
	private RespondentRepository respondentRepository;
	
	@Test
	public void createRespondentWithNoName() {
		Respondent uus = new Respondent();
		respondentRepository.save(uus);
		
		Optional<Respondent> check = respondentRepository.findById((long) 1);
		if ( check.isPresent() ) {
			Respondent uus2 = check.get();
			assertThat(uus2.getFirstname().isEmpty());
		}
	}
	
	@Test
	public void createRespondentWithName() {
		Respondent uus = new Respondent("Testi", "Teppo");
		respondentRepository.save(uus);
		
		Optional<Respondent> check = respondentRepository.findById((long) 1);
		if ( check.isPresent() ) {
			Respondent uus2 = check.get();
			assertThat(uus2.getFirstname().equals("Testi"));
		} else {
			assert(false);
		}
	}
}