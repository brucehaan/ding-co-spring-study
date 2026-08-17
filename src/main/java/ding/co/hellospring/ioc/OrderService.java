package ding.co.hellospring.ioc;

/*
나쁜 코드 (IoC 미적용 : 내가 직접 new)
점원(OrderService)이 특정 카드사 기계(CardPayment)를 직접 사서 쓰고 있음
 */
//public class OrderService {
//    // 문제점 : '신용카드'라는 구체적인 부품에 딱 달라붙어 있음 (강한 결합)
//    private final CardPayment payment = new CardPayment();
//
//    public void pay(int money) {
//        payment.pay(money); // 신용카드로만 결제 가능
//    }
//}
// 재앙 발생 : 사장님 "오늘부터 신용카드 치우고 카카오페이로 바꿉시다!" 개발자 "잠시만요.."

/*
좋은 코드 (IoC적용 : 감독이 주는 대로 씀)
점원 (OrderService)은 결제 기계가 뭔지 모름. 그냥 "결제 가능한 거 (Interface) 하나만 달라고 함
 */

// 1. 규격(Interface)을 먼저 만듦
interface PaymentPolicy {
    void pay(int money);
}

// 2. 구현체들은 이 규격을 따름
class CardPayment implements PaymentPolicy {
    @Override
    public void pay(int money) {

    }
}

class KakaoPay implements PaymentPolicy {
    @Override
    public void pay(int money) {

    }
}

// 3. 점원 (Service)
public class OrderService {
    private final PaymentPolicy payment; // 구체적인 이름 (card, kakao)이 없음

    // 생성자 : 뭐가 들어올지는 모르지만 아무튼 결제되는 거 하나만 줘
    public OrderService(PaymentPolicy payment) {
        this.payment = payment;
    }

    public void pay(int money) {
        payment.pay(money); // 뭐가 들어왔든 난 결제 버튼만 누름
    }
}

// 사장님 "카카오페이로 바꿉시다!" 개발자 : OrderService 코드는 한 줄도 건드리지 않음