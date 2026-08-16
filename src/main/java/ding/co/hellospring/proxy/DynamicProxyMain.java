package ding.co.hellospring.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxyMain {
    public static void main(String[] args) {
        Singer realIU = new IU();
        Singer robotProxy = (Singer) Proxy.newProxyInstance(
                Singer.class.getClassLoader(),
                new Class[]{Singer.class},
                new RobotManager(realIU)
        );
        System.out.println("이제 로봇 매니저 가동");
        robotProxy.sing();
        System.out.println("이제 로봇 매니저의 정체는");
        System.out.println(robotProxy.getClass().getName());
    }
}
