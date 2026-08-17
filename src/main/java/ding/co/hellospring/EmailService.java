package ding.co.hellospring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final UserService userService;

    void send(String email, String content) {

    }

    void receive(String email, String content) {
        userService.join(new User(email, 20));
    }
}
