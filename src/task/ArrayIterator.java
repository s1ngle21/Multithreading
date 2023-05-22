package task;

public class ArrayIterator implements Runnable {
    double[] values;

    public ArrayIterator(double[] values) {
        this.values = values;
    }

    @Override
    public void run() {
        for (int i = 0; i < values.length; i++) {
            values[i] = (float)(values[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
