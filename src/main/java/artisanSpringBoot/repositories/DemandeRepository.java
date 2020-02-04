package artisanSpringBoot.repositories;

import java.util.Date;
import java.util.List;

import org.hibernate.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Compte;
import artisanSpringBoot.model.Demande;
import artisanSpringBoot.model.Metier;
import artisanSpringBoot.model.StatutDemande;

public interface DemandeRepository extends JpaRepository<Demande, Long>{
	
	List<Demande> findByIdDemande(Long idDemande);
	List<Demande> findByArtisan(Compte idArtisan);
	List<Demande> findByClient(Compte idClient);
	List<Demande> findByService(Service idService);
	List<Demande> findByMetier(Metier idMetier);
	List<Demande> findByStatut(StatutDemande statut);
	List<Demande> findByDateBetween(Date date_min, Date date_max);
	List<Demande> findByDateBefore(Date date);
	List<Demande> findByDateAfter(Date date);

}
