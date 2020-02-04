package artisanSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Metier;

public interface MetierRepository extends JpaRepository<Metier, Long> 
{
	List<Metier> findByTitreMetier(String titreMetier);
}
