package artisanSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Metier;

public interface MetierRepository extends JpaRepository<Metier, Integer> 
{
	List<Metier> findByTitreMetier(String titreMetier);
}
