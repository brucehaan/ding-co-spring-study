package ding.co.hellospring.service;

import ding.co.hellospring.model.Point;
import ding.co.hellospring.model.User;
import ding.co.hellospring.repository.PointRepository;
import ding.co.hellospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    /*
    1. 필드 주입 (Field Injection) - 초창기 배달
    가장 쉽고 간편해서 예전에 많이 썼던 방식. 변수 위에 @Autowired 스티커만 딱 붙이면 됨
    장점 : 코드가 짧고 쉽다
    단점 : 치명적이다?
       - 테스트 불가 : 스프링 없이 순수 자바 단위 테스트하려고 하면, userRepository에 가짜 객체를 넣을 방법이 없음
       (필드가 private이라서 외부에서 접근 불가)
       - 절대 쓰지 마셈(?)
     */
//    @Autowired // 스프링님, 여기에 넣어주세요
//    private UserRepository userRepository;

    /*
    2. 수정자 주입 (Setter Injection) - 선택적 배달
    setXxx 메서드를 통해 주입받는 방식
    특징 : 중간에 의존성을 바꿀 수 있음
    단점 : public 으로 열려 있어서 누군가 실수로 set...을 호출해 객체를 바꿔버릴 위험이 있음.
    대부분의 의존 관계는 한번 맺으면 앱이 종료될 때까지 변하면 안 됨
     */
//    private UserRepository userRepository;
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    /*
    3. 생성자 주입 (Constructor Injection) - 공식 추천
    현재 스프링팀이 강력 추천하는 표준 방식.
    장점 1 (불변) : final을 붙일 수 있음. 즉, 한 번 주입되면 평생 바뀌지 않음. 안전함.
    장점 2 (누락 방지) : 만약 new UserService()를 할 때 userRepository를 안 넣으면? 컴파일 오류가 나서 바로 알 수 있음
     */
    // 3-1. final 키워드 사용 가능 (불변성)
    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    // 3-2. 생성자에서 주입
    // @Autowired (생성자가 1개면 생략 가능)
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    @Transactional
    public User join(User user) {
        // 중복 회원 검증
        validateDuplicateNameUser(user);

        calculateGrade(user);

        User savedUser = userRepository.save(user);
        Point point = new Point(savedUser.getId(), 1000);
        pointRepository.save(point);

        return savedUser;
    }

    private void validateDuplicateNameUser(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 가입된 이메일입니다.");
                });
    }

    private void calculateGrade(User user) {
        if (user.getAge() > 50) {
            user.setGrade("VIP");
        } else {
            user.setGrade("NORMAL");
        }
    }
}
