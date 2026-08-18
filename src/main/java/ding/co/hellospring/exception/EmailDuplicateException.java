package ding.co.hellospring.exception;

// Exception (checked) -> 컴파일러 단에서 아주 깐깐하게 감 시 -> 그래서 둘 중 하나를 해야 함
// 1 ) try catch
// 2 ) throws 를 붙여서 난 몰라, 나를 부른 놈이 알아서 처리해
// RuntimeException (Unchecked)
// 컴파일러가 신경 쓰지 않음. 코드짤 때 아무런 에러표시가 없고, 프로그램이 실행되는 도중에 에러가 발생하기 때문에 런타임 Exception
// try catch 가 없더라도 크게 코드 구현시에 문제가 없음
// Spring Transactional -> checked exception 롤백을 하지 않음
// Unchecked Exception -> 발생하게 되면 자동 롤백
public class EmailDuplicateException extends RuntimeException {
    public EmailDuplicateException(String message) {
        super(message);
    }
}
