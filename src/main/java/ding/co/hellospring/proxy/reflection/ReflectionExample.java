package ding.co.hellospring.proxy.reflection;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 1. 실험 대상 클래스
class Person {
    public void sayHello(String name) {
        System.out.println(" 안녕? 나는 " + name + " 라고 해.");
    }
}

public class ReflectionExample {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();

        // 방식 1 : 일반적인 호출 (우리가 아는 방식)
        System.out.println("1. 일반 호출");
        person.sayHello("철수");

        // 방식 2 : 리플렉션 호출 (마법의 거울)
        System.out.println("2. 리플렉션 호출");

        // 1. 클래스 정보 (거울 ) 가져오기
        Class<?> clazz = person.getClass();

        // 2. 메서드 정보 찾기 ("sayHello"라는 이름의 메서드를 찾아라!)
        // 파라미터로 String.class를 받는다는 것까지 명시해야 함
        Method methodInfo = clazz.getMethod("sayHello", String.class);

        // 3. 실행 (invoke)
        // 해석 : methodInfo(지침)대로 실행해라. 대상은 person이고, 재료는 '영희'다.
        methodInfo.invoke(person, "영희");
    }
}
