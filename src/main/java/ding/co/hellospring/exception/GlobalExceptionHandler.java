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
// 5. @RestControllerAdvice 스티커
// 나는 레스토랑 지배인이다. 모든 @RestController를 지켜본다
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 6. @ExceptionHandler 메뉴얼
    // 만약 UserNotFoundException 소란이 터지면, 이 메서드를 실행해라.
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {

        // 7. 표준 에러 그릇에 에러 코드와 메시지를 담는다.
        ErrorResponse errorResponse = new ErrorResponse("USER_NOT_FOUND", ex.getMessage());
        // 8. 응답 봉투에
        // 1) 내용물 (response)
        // 2) HTTP 신호등 (HttpStatus.NOT_FOUND -> 404)
        // 을 담아서 손님(클라이언트)에게 최종 응답한다.
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // 9. @ExceptionHandler 메뉴얼
    // 만약 EmailDuplicateException 소란이 터지면, 이 메서드를 실행해라.
    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<ErrorResponse> handleEmailDuplicateException(EmailDuplicateException ex) {
        ErrorResponse response  = new ErrorResponse("EMAIL_DUPLICATE", ex.getMessage());
        // 10. 신호등이 HttpStatus.BAD_REQUEST 400 으로 바뀐 것을 주목
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 11. 그 외 모든 주방 화재(500) 용 메뉴얼
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse response = new ErrorResponse("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
