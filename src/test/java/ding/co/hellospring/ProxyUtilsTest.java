package ding.co.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProxyUtilsTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void checkTarget() {
        System.out.println("겉모습(Proxy)" + userRepository.getClass());
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(userRepository);
        System.out.println("알맹이(target): " + targetClass);
    }
}
