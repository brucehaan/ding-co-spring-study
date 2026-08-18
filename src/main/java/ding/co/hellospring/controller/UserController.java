package ding.co.hellospring.controller;

import ding.co.hellospring.service.UserService;
import ding.co.hellospring.model.User;
import ding.co.hellospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/users")
    public User signUp(
            @RequestBody
            User newUser
    ) {
        return userService.join(newUser);
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
