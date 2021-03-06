package artisanSpringBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import artisanSpringBoot.model.Adresse;
import artisanSpringBoot.model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
	
	
	List<Compte> findByEmail(String mail);
	List<Compte> findByAdresse(Adresse adresse);
	
	@Query("select distinct c from Compte c left join fetch c.roles where c.idCompte=:idCompte")
	Optional<Compte> findByIdWithRoles(@Param("idCompte") Integer idCompte);
	@Query("select distinct c from Compte c left join fetch c.roles where c.login=:login")
	Optional<Compte> findByLoginWithRoles(@Param("login") String login);
	Optional<Compte> deleteByIdCompte(Integer id);
	Optional<Compte> findByIdCompte(Integer id);
	
}
