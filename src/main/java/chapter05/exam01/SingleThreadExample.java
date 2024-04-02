package chapter05.exam01;

public class SingleThreadExample {
    /**
     * [ Single Thread ]
     * 단일 스레드 프로세스의 장단점
     * 1. 문맥교환이 없다. <-> CPU의 멀티코어를 활용하지 못한다.
     * 2. 동기화 이슈가 없다. <-> 순차적 실행으로 응답성 및 전체 처리량이 낮다.
     * 3. 자원 비용이 적다. <-> I/O 처리 시 CPU가 낭비된다.
     * 4. 프로그래밍 난이도가 낮다. <-> 스레드에 오류가 발생하면 프로그램이 종료된다.
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
            try {
                Thread.sleep(1);
                //      throw new RuntimeException("error"); // 스레드에 오류가 발생하면 프로그램이 종료된다.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" 합계 : " + sum);
        System.out.println("싱글 스레드 처리ㅣ 시간 : " + (System.currentTimeMillis() - start) + "ms");

    }
}
