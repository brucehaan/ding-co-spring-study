package ding.co.hellospring.controller;

import ding.co.hellospring.dto.TodoCreateRequest;
import ding.co.hellospring.model.Todo;
import ding.co.hellospring.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTodo(
            @RequestParam Long userId,
            @Valid @RequestBody TodoCreateRequest request
    ) {
        Todo todo = todoService.createTodo(userId, request);
        return new ResponseEntity<>(todo, CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodoByUser(
            @RequestParam Long userId
    ) {
        List<Todo> todos = todoService.getTodosByUser(userId);
        return new ResponseEntity<>(todos, OK);
    }

    @PatchMapping("/{todoId}/complete")
    public ResponseEntity<Todo> completeTodo(
            @RequestParam Long userId,
            @PathVariable Long todoId
    ) {
        Todo updatedTodo = todoService.completeTodo(userId, todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @RequestParam Long userId,
            @PathVariable Long todoId
    ) {
        todoService.deleteTodo(userId, todoId);
        return ResponseEntity.ok().build();
    }
}
