package ding.co.hellospring;

import ding.co.hellospring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProxyTest {
    @Autowired
    UserService userService;

    @Test
    void printClass() {
        System.out.println("원래 클래스 이름");
        System.out.println(UserService.class.getName());

        System.out.println("주입받은 객체의 이름");
        System.out.println(userService.getClass().getName());
    }
}
