package ding.co.hellospring.proxy;

public class ConcertMain {
    public static void main(String[] args) {
        Singer singer = new IU();
        Singer manager = new IUManager(singer);
        System.out.println("공연 시작!");
        manager.sing();

    }
}
