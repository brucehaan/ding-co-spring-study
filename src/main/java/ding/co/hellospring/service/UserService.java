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

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

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
