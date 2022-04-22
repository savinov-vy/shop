package ru.savinov.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.savinov.shop.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    @Modifying
    @Query("update User p set p.enabled = ?2 where p.id = ?1")
    void desableUserById(Long id, Boolean enabled);

    @Modifying
    @Query("update User p set p.enabled = ?2 where p.id = ?1")
    void enableUserById(Long id, Boolean enabled);
}

