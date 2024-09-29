package chapter11.exam09;

import java.util.concurrent.ForkJoinPool;

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
