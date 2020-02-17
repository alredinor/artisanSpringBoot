package artisanSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Adresse;
import artisanSpringBoot.model.Artisan;


public interface ArtisanRepository extends JpaRepository<Artisan, Integer> {
	
	
	List<Artisan> findByLogin(String login);
	List<Artisan> findByAdresse(Adresse adresse);
	

	
}
