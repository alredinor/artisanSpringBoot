package artisanSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Adresse;
import artisanSpringBoot.model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	
	List<Compte> findByIdCompte(Long idCompte);
	List<Compte> findByEmail(String mail);
	List<Compte> findByAdresse(Adresse adresse);
	
}
