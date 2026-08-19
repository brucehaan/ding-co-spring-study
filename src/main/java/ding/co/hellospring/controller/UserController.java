package ding.co.hellospring.controller;

import ding.co.hellospring.dto.UserCreateRequest;
import ding.co.hellospring.dto.UserResponse;
import ding.co.hellospring.service.UserService;
import ding.co.hellospring.model.User;
import ding.co.hellospring.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/users")
    public User signUp(
            @Valid @RequestBody UserCreateRequest request
    ) {
        return userService.join(request);
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User("Dingco", 30);
        return user;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> getUserList(Pageable pageable) {
        Page<User> users = userService.findUsers(pageable);
        return ResponseEntity.ok(users.map(UserResponse::new));
    }
}
