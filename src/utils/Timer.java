package utils;

public class Timer {
    public static long startTime = 0;
    public static long endTime = 0;

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static void stop() {
        endTime = System.currentTimeMillis();
    }

    public static long get() {
        return endTime - startTime;
    }
}
