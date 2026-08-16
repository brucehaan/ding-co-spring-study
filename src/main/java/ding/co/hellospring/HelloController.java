package ding.co.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    private final UserRepository userRepository;

    @Autowired
    public HelloController(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
    }


    @PostMapping("/users")
    public User createUser(
            @RequestBody
            User newUser
    ) {
        return userRepository.save(newUser);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User("Dingco", 30);
        return user;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
