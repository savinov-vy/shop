package ru.savinov.spring.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.savinov.spring.shop.entities.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Query("update User p set p.enabled = ?2 where p.id = ?1")
    public void desableUserById(Long id, Boolean enabled);

    @Modifying
    @Query("update User p set p.enabled = ?2 where p.id = ?1")
    public void enableUserById(Long id, Boolean enabled);

}

