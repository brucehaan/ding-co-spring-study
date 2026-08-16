package ding.co.hellospring;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/users")
    public List<User> getUserList() {
        User user1 = new User("Dingco", 30);
        User user2 = new User("Dingco", 40);
        return List.of(user1, user2);
    }
}
