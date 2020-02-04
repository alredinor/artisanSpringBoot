package formation.sopra.formationSpringBoot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import artisanSpringBoot.repositories.SalleRepository;

@SpringBootTest
class FormationSpringBootApplicationTests {

	@Autowired
	private SalleRepository salleRepository;

	@Test
	void contextLoads() {
		assertNotNull(salleRepository);
	}

}
