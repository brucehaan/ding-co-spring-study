package ding.co.hellospring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

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
        User user1 = new User("Dingco", 30);
        User user2 = new User("Dingco", 40);
        return List.of(user1, user2);
    }
}
