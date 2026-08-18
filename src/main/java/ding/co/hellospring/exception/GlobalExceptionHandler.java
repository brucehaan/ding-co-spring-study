package ding.co.hellospring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 RestControllerAdvice에 대해서 어노테이션을 붙여놓은 것들을 findAnnotatedBeans에서 찾아줌.
 그리고 그 찾은 것들을 가지고 ExceptionHandlerExceptionResolver에서 등록을 해둠.
 그러고 나서 실제 에러가 발생하면, DispatcherServlet에서 ExceptionHandlerExceptionResolver야 너 혹시 이미 등록된 에러 처리 로직 없니? 라고 물어봄
 그러면, 어 이 에러 타입에 대해서는 이미 등록되어있는 메서드가 있네요? 바로 handleUserNotFoundException 메서드입니다.
 라고 한 다음에 아 그러면 이 로직 실행시킬게, 하고 나서 반환값이 ResponseEntity 네? 그럼 ResponseEntity이거에 맞춰서 응답값을 포맷해서 내려줄게.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("USER_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
