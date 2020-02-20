package artisanSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import artisanSpringBoot.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

}
