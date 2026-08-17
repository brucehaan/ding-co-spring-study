package ding.co.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class ApplicationContextInfoTest {

    @Autowired
    ApplicationContext ac;



    @Test
    void findAllBean() {
        String[] allBeanNames = ac.getBeanDefinitionNames();
        for (String name : allBeanNames) {
            Object bean = ac.getBean(name);
            System.out.println("bean = " + bean);
        }
    }
}
