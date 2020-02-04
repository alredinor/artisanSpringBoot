package artisanSpringBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Adresse;
import model.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{
	
	
	List<Client> findByIdCompte(Long idCompte);
	List<Client> findByAdresse(Adresse adresse);
	List<Client> findByLogin(String login);
	List<Client> findByEmail(String email);
}
