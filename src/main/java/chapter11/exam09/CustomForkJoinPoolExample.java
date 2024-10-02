package chapter11.exam09;

import java.util.concurrent.ForkJoinPool;

/**
 * [ForkJoinPool]
 * 작업을 작은 조각으로 나누고 병렬로 처리하여, 다중 코어 프로세서에서 효율적으로 작업을 수행할 수 있도록 도와준다.
 * 기본적으로 어플리케이션에서 공용으로 사용하는 스레드는 CPU 코어 개수 -1개 만큼 생성된다. (Runtime.getRuntime().availableProcessors() - 1)
 * ForkJoinPool.commonPool()은 전체 어플리케이션에서 스레드를 공용으로 사용하기 때문에 다음과 같은 주의가 필요하다
 * 1. 스레드 블로킹 : I/O 바운드 작업은 스레드를 블록시키는 작업으로 commonPool()에서 실행 시 스레드 부족으로 다른 작업이 지연 될 수 있다.
 * -> 별도의 스레드 풀을 생성하여 I/O작업과 CPU작업을 분리하고 I/O작업을 별도의 스레드에서 처리하는 것을 고려해야한다.
 * 2. Starvation(기아상태) : I/O 작업이 지속적으로 블록되면 CPU 작업이 실행 기회를 얻지 못하고, 기아상태에 빠질 수 있다.
 * -> 스레드 풀을 분리하여 CPU작업이 충분한 실행 기회를 얻도록 관리해야하고 대신 스레드가 필요 이상으로 생성되어 리소스 비요잉 커지지 않도록 해야 한다.
 */
public class CustomForkJoinPoolExample {
    public static void main(String[] args) {

        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        CustomRecursiveTask task = new CustomRecursiveTask();
        Integer result = pool.invoke(task);

        System.out.println("result = " + result);
    }
}
