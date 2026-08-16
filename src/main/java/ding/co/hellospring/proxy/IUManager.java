package ding.co.hellospring.proxy;

public class IUManager implements Singer{
    private final Singer realSinger;

    public IUManager(Singer realSinger) {
        this.realSinger = realSinger;
    }

    @Override
    public void sing() {
        // 부가 기능 1
        System.out.println("마이크테스트");

        // 실제 객체를 호출에 역할을 위임
        realSinger.sing();

        // 부가 기능 2
        System.out.println("감사합니다");
    }
}
