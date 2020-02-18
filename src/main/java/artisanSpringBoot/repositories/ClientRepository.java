package artisanSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Adresse;
import artisanSpringBoot.model.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	
	List<Client> findByIdCompte(Integer idCompte);
	List<Client> findByAdresse(Adresse adresse);
	List<Client> findByLogin(String login);
	List<Client> findByEmail(String email);
}
