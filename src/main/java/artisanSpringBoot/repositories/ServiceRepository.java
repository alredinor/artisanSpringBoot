package artisanSpringBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Metier;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	Optional<Service> findByIdService(Long idService);
	List<Service> findByNomService(String nomService);
	List<Service> findByMetier(Metier idMetier);
}
