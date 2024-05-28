package org.example.javalt;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.javalt.Entities.Role;
import org.example.javalt.Entities.User;
import org.example.javalt.Repositories.RoleRepository;
import org.example.javalt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@SpringBootApplication
public class JavaLtApplication {

//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(JavaLtApplication.class, args);
    }
//    @PostConstruct
//    public void init() {
//        Role adminRole = new Role("1", "ADMIN", null);
//        Role userRole = new Role("2", "USER", null);
//
//        roleRepository.saveAll(Arrays.asList(adminRole, userRole));
//
//        User user1 = new User("u1", "user1", "password1", "user1@example.com", "First1", "Last1", false, adminRole);
//        User user2 = new User("u2", "user2", "password2", "user2@example.com", "First2", "Last2", false, userRole);
//        User user3 = new User("u3", "user3", "password3", "user3@example.com", "First3", "Last3", false, userRole);
//
//        userRepository.saveAll(Arrays.asList(user1, user2, user3));
//    }

}
