package artisanSpringBoot.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Metier;
import artisanSpringBoot.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
	
	Optional<Service> findByIdService(Integer idService);
	List<Service> findByNomService(String nomService);
	List<Service> findByMetier(Metier idMetier);
}
