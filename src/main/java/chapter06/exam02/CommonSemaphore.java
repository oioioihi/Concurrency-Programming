package chapter06.exam02;

/**
 * [ 뮤택스와 세마포어의 차이 ]
 * 1. 동작방식
 * 뮤택스 : 공유 자원에 대한 접근을 동시에 하나의 스레드만 가능하도록 보장한다. 즉, 뮤택스는 상호배제를 위한 동기화 기법이다.
 * 세마포어 : 카운팅 기법으로, 특정 개수의 스레드가 동시에 공유 자원에 접근할 수 있도록 제어한다.
 * <p>
 * 2. 소유권
 * 뮤택스 : 뮤택스는 소유권이 있어서 락을 획득한 스레드만이 락을 해제할 수 있다. 즉, 락을 획득한 스레드가 락을 해제하지 않으면 다른 스레드는 해당 뮤택스에 접근할 수 없다.
 * 세마포어 : 소유권의 개념이 없으며, 특정 개수의 스레드가 동시에 접근을 허용하는 카운팅 기법으로 작동한다. 따라서 세마포어를 사용하는 스레드들이 모두 세마포어를 해제할 수 있다.
 * <p>
 * 3. 초기값
 * 뮤택스 : 뮤택스는 기본적으로 잠겨있는 상태로 시작한다. 한 스레드가 뮤택스를 획득하여 자원에 접근하면 다른 스레드들은 해당 뮤택스를 획득하기 위해 블로킹된다.
 */
public interface CommonSemaphore {

    void acquired();

    void release();
}
