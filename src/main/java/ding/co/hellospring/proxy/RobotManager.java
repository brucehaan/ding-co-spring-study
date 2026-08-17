package ding.co.hellospring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 이 클래스는 매니저 자체가 아니라, 매니저의 두뇌(메뉴얼)이다
public class RobotManager implements InvocationHandler {

    /*
    1. 담당할 연예인 (Object)
    이전에는 'Singer'라고 딱 박았지만, 이제는 'Object'이다.
    즉, 아이유든 BTS든 심지어 일반인도 다 담당할 수 있다.
     */
    private final Object target;

    public RobotManager(Object target) {
        this.target = target;
    }


    /*
    2. 업무 수행 (invoke)
    어떤 요청(method)이 들어오든 무조건 이 함수가 실행된다.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("매니저가 바닥을 씁니다");

        /*
        위임 - 진짜에게 토스 (Reflection)
        해석 : Target아, 지금 들어온 method(명령)를 args(재료) 가지고 실행해라!
        (자바의 '리플렉션'이라는 마법 기술을 사용한다.
         */
        Object result = method.invoke(target, args);
        System.out.println("매니저가 인사를 합니다");
        return result;
    }
}
