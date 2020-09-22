package ru.savinov.spring.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savinov.spring.shop.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
