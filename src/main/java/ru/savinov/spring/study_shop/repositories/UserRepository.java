package ru.savinov.spring.study_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.savinov.spring.study_shop.entities.Product;
import ru.savinov.spring.study_shop.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    @Modifying
//    @Query(value = "insert into users (username, password, enabled) values (username = ?1, password = ?2, enabled = true")
//    void createNewUser( String newUserName, String password);

 //   INSERT INTO users (username,password,enabled) values ('U' , '{noop}a', true);

}
