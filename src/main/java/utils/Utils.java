package utils;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Utils {

    public static ArrayBlockingQueue queueA = new ArrayBlockingQueue<>(100);
    public static ArrayBlockingQueue queueB = new ArrayBlockingQueue<>(100);
    public static ArrayBlockingQueue queueC = new ArrayBlockingQueue<>(100);

    public static int findMaxCharCount(ArrayBlockingQueue queue, char letter) {
        int count = 0;
        int max = 0;
        String text;
        try {
            for (int i = 0; i < 10_000; i++) {
                text = (String) queue.take();
                for (char c : text.toCharArray()) {
                    if (c == letter) count++;
                }
                if (count > max) max = count;
                count = 0;
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName());
            return -1;
        }
        return max;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
