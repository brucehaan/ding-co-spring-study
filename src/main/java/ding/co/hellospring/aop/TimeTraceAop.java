package ding.co.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 임수 수행 비밀 요원. 누가
@Component
public class TimeTraceAop {

    // 포인트컷. 작전 구역 및 타겟팅. 어디서
    @Around("execution(* ding.co.hellospring..*(..))")
    // 어드바이스. 해야 할 임무. 무엇을
    // joinPoint는 AOP를 적용할 수 있는 실행 지점 (메서드 호출, 생성자 호출)
    // 즉, 프로그램이 실행되다가 요원이 끼어들 수 있는 모든 틈새
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("지금 호출된 메서드는 무엇입니다 " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println(" 끝 : " + timeMs + " ms");
        }
    }
}
