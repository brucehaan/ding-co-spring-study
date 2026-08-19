package ding.co.hellospring.dto;

import ding.co.hellospring.model.User;

public class UserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final int age;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
    }
}
