package ding.co.hellospring.service;

import ding.co.hellospring.dto.TodoCreateRequest;
import ding.co.hellospring.exception.UserNotFoundException;
import ding.co.hellospring.model.Todo;
import ding.co.hellospring.model.User;
import ding.co.hellospring.repository.TodoRepository;
import ding.co.hellospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public Todo createTodo(Long userId, TodoCreateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("투두를 생성하려는데 유저가 없네요."));
        Todo newTodo = request.toEntity(user);
        return todoRepository.save(newTodo);
    }

    public Todo completeTodo(Long userId, Long todoId) {
        // 할 일 : todo가 존재하는지 파악
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없다."));
        // 이 할 일이 userId의 것이 맞는지 검증해야 한다.
        todo.validateOwnership(userId);
        todo.complete();
        return todo;
    }

    public void deleteTodo(Long userId, Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다."));
        todo.validateOwnership(userId);
        todoRepository.delete(todo);
    }

    public List<Todo> getTodosByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("ID " + userId + "에 대한 사용자를 찾을 수 없습니다.")
        );
        return todoRepository.findByUser(user);
    }
}
