package ding.co.hellospring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자의 고유 id (식별자)

    private String name;
    private int age;
    private String grade;
    private String email;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
