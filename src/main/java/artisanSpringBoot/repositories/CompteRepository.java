package artisanSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	
	List<Compte> findByIdCompte();
	List<Compte> findByEmail();
	List<Compte> findByAdresse();
	
}
