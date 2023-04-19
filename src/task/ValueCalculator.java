package task;

import java.util.Arrays;

public class ValueCalculator {
    private double[] numbers;
    private int size;
    private int halfSize;

    public ValueCalculator(int size) {
        if (size < 1_000_000) {
            throw new IllegalArgumentException("Array size must be over 1 000 000");
        }
        this.size = size;
        this.halfSize = size / 2;
        this.numbers = new double[size];
    }

    public double[] getNumbers() {
        return numbers;
    }

    public int getSize() {
        return size;
    }

    public int getHalfSize() {
        return halfSize;
    }


    public void actionsWithArray()  {
        long start = System.currentTimeMillis();
        Arrays.fill(numbers, 1);
        double[] a1 = new double[halfSize];
        double[] a2 = new double[halfSize];
        System.arraycopy(numbers, 0, a1, 0, halfSize);
        System.arraycopy(numbers, halfSize, a2, 0, halfSize);

        Thread thread1 = new Thread(new ArrayIterator(a1));
        Thread thread2 = new Thread(new ArrayIterator(a2));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(a1, 0, numbers, 0, halfSize);
        System.arraycopy(a2, 0, numbers, halfSize, halfSize);

        long end = System.currentTimeMillis();

        System.out.println("It took " + (end - start) + " milliseconds to end this task");
    }

}
