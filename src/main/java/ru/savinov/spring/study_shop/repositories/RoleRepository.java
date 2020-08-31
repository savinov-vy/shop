package ru.savinov.spring.study_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savinov.spring.study_shop.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
