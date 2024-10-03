package chapter11.exam09;

import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private final int[] array;
    private final int start;
    private final int end;

    public CustomRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start < THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = start + (end - start) / 2;
            CustomRecursiveTask left = new CustomRecursiveTask(array, start, mid);
            CustomRecursiveTask right = new CustomRecursiveTask(array, mid, end);

            left.fork(); // 다른 스레드에서 작업을 실행
            Integer rightResult = right.compute();
            Integer leftResult = left.join(); // 작업이 완료 될때까지 대기
            return leftResult + rightResult;
        }

    }
}
