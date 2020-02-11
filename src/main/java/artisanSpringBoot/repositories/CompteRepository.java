package artisanSpringBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import artisanSpringBoot.model.Adresse;
import artisanSpringBoot.model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	
	Optional<Compte> findByIdCompte(Integer id);
	List<Compte> findByEmail(String mail);
	List<Compte> findByAdresse(Adresse adresse);
	
	@Query("select distinct c from Compte c left join fetch c.roles where c.idCompte=:idCompte")
	Optional<Compte> findByIdWithRoles(Integer idCompte);
	Optional<Compte> deleteByIdCompte(Integer id);
	
}
