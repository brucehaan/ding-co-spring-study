package ding.co.hellospring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RobotManager implements InvocationHandler {

    private final Object target;

    public RobotManager(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("매니저가 바닥을 씁니다");
        Object result = method.invoke(target, args);
        System.out.println("매니저가 인사를 합니다");
        return result;
    }
}
