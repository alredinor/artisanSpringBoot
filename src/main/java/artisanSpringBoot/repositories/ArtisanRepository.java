package artisanSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Adresse;
import model.Artisan;


public interface ArtisanRepository extends JpaRepository<Artisan, Long> {
	
	
	List<Artisan> findByLogin(String login);
	List<Artisan> findByAdresse(Adresse adresse);
	

	
}
