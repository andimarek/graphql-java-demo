package com.example;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRepository {

    public static class User {

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String id;
        public String name;
    }


    private List<User> users = new ArrayList<>();


    @PostConstruct
    public void init() {
        users.add(new User("1", "Yoda"));
        users.add(new User("2", "Luke"));
        users.add(new User("3", "Leia"));
        users.add(new User("4", "Padme"));
    }


    public User getUser(String id) {
        List<User> found = users.stream().filter(user -> user.id.equals(id)).collect(Collectors.toList());
        return found.size() > 0 ? found.get(0) : null;
    }

    public List<User> getAllUses() {
        return users;
    }
}
