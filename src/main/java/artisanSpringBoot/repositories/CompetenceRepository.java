package artisanSpringBoot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
	Page<Competence> findAllByNomContaining(String nom, Pageable pageable);

}
