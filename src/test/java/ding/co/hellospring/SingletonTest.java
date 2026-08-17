package ding.co.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SingletonTest {

    @Autowired
    ApplicationContext ac;

    @Autowired
    UserRepository userRepository;

    @Test
    void pureJava() {
        UserService userService1 = new UserService(userRepository);
        UserService userService2 = new UserService(userRepository);

        System.out.println("userService1 = " + userService1);
        System.out.println("userService2 = " + userService2);
    }

    @Test
    void springSingleton() {
        Object bean1 = ac.getBean(UserService.class);
        Object bean2 = ac.getBean(UserService.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
    }
}
