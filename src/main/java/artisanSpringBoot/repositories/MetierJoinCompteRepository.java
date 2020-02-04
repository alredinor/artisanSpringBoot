package artisanSpringBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Compte;
import artisanSpringBoot.model.Metier;
import artisanSpringBoot.model.MetierJoinCompte;

public interface MetierJoinCompteRepository extends JpaRepository<MetierJoinCompte, Long> {
	
	
	Optional <MetierJoinCompte> findById (Long id);
	List <MetierJoinCompte> findByMetier(Metier metier);
	List <MetierJoinCompte> findByCompte(Compte compte);
}
