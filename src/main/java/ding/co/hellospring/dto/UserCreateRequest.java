package ding.co.hellospring.dto;

import ding.co.hellospring.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @Min(value = 1, message = "나이는 1살 이상이어야만 합니다.")
    private int age;

    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    public User toEntity() {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setAge(age);
        return newUser;
    }
}
