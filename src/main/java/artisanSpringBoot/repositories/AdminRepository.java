package artisanSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
