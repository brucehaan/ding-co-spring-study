package ding.co.hellospring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed = false;

    @ManyToOne
    private User user;

    public Todo(String title, User user) {
        this.title = title;
        this.user = user;
    }

    // 완료 처리하라 라는 비즈니스 메서드 추가
    public void complete() {
        this.completed = true;
    }

    // 검증하는 메서드. 지금 요청한 유저가 TODO의 주인이 맞는지 검증해달라.
    public void validateOwnership(Long userId) {
        if (!user.getId().equals(userId)) {
            throw new RuntimeException("권한이 없는 것 같습니다." + user.getId() + " != " + userId);
        }
    }
}
