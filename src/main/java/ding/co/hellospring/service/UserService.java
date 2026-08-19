package ding.co.hellospring.service;

import ding.co.hellospring.dto.UserCreateRequest;
import ding.co.hellospring.exception.NameDuplicateException;
import ding.co.hellospring.exception.UserNotFoundException;
import ding.co.hellospring.model.Point;
import ding.co.hellospring.model.User;
import ding.co.hellospring.repository.PointRepository;
import ding.co.hellospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    @Transactional
    public User join(UserCreateRequest request) {
        User newUser = request.toEntity();

        // 중복 회원 검증
        validateDuplicateNameUser(newUser);

        calculateGrade(newUser);

        User savedUser = userRepository.save(newUser);
        log.info("회원 저장 성공");
        Point point = new Point(savedUser.getId(), 1000);
        pointRepository.save(point);
        log.info("회원 등록 성공");
        return savedUser;
    }

    private void validateDuplicateNameUser(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(u -> {
                    log.warn("이름 중복 시도 감지 : {}", user.getName());
                    throw new NameDuplicateException("이미 가입된 이메일입니다.");
                });
    }

    private void calculateGrade(User user) {
        if (user.getAge() > 50) {
            user.setGrade("VIP");
        } else {
            user.setGrade("NORMAL");
        }
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("ID " + id + "에 대한 사용자를 찾을 수 없다.")
        );
    }

    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
