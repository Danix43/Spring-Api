package danix43.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import danix43.api.Start;
import danix43.api.models.Termometru;
import danix43.api.models.database.TermometreRepository;

@SpringBootTest(classes = Start.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class TermometruTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private TermometreRepository termometreRepository = Mockito.mock(TermometreRepository.class);
	
	@Test
	public void whenFindByMachineName_thenReturnTermometru() {
		Termometru ter1 = new Termometru();
		ter1.setMachinename("Poseidon01");
		entityManager.persist(ter1);
		entityManager.flush();
		
		Termometru terFound = termometreRepository.findByMachinename(ter1.getMachinename());
		
		assertThat(terFound.getMachinename())
		.isEqualTo(ter1.getMachinename());
	}

}
