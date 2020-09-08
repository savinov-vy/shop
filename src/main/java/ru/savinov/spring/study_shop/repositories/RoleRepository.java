package ru.savinov.spring.study_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savinov.spring.study_shop.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
