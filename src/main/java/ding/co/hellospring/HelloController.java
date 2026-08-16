package ding.co.hellospring;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    private Map<Long, User> users = new HashMap<>();

    private Long nextId = 1L;

    @PostMapping("/users")
    public User createUser(
            @RequestBody
            User newUser
    ) {
        newUser.setId(nextId++);
        users.put(newUser.getId(), newUser);
        return newUser;
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
        String userName = "Dingco" + id;
        int age = 20 + id.intValue();
        User user = new User(userName, age);
        return user;
    }

    @GetMapping("/users")
    public List<User> getUserList() {
        return users.values().stream().toList();
    }
}
