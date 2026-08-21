package ding.co.hellospring.dto;

import ding.co.hellospring.model.Todo;
import ding.co.hellospring.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoCreateRequest {

    @NotBlank
    private String title;

    public Todo toEntity(User user) {
        return new Todo(
                title,
                user
        );
    }
}
