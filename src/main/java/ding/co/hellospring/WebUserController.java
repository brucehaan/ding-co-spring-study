package ding.co.hellospring;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebUserController {
    // 이제 Repository가 아니라 Service(매니저)를 부릅니다.
    private final UserService userService;

    @PostMapping("/web/users")
    public User signUp(@RequestBody User user) {
        // 점원의 할 일 끝 : 매니저님, 가입 처리해주세요.
        return userService.join(user);
    }
}
