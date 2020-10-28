package ru.savinov.spring.shop.services;

import org.springframework.stereotype.Service;
import ru.savinov.spring.shop.entities.UserA;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceA {

    private List<UserA> users;

    //Love Java 8
    public List<UserA> findByUserNameOrEmail(String username) {

        List<UserA> result = users.stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());

        return result;

    }

    //Init some users for testing
    @PostConstruct
    private void iniDataForTesting() {

        users = new ArrayList<UserA>();

        UserA user1 = new UserA("mkyong", "password111", "mkyong@yahoo.com");
        UserA user2 = new UserA("yflow", "password222", "yflow@yahoo.com");
        UserA user3 = new UserA("laplap", "password333", "mkyong@yahoo.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);

    }

}
