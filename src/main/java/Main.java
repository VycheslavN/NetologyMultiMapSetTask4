import static utils.Utils.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread textGenerator = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                String text = generateText("abc", 100_000);
                try {
                    queueA.put(text);
                    queueB.put(text);
                    queueC.put(text);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        });
        textGenerator.start();

        Thread thread1 = new Thread(() -> {
            char letter = 'a';
            int maxA = findMaxCharCount(queueA, letter);
            System.out.println("Максимальное количество символов '" + letter + "' в тексте: " + maxA);
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            char letter = 'b';
            int maxB = findMaxCharCount(queueB, letter);
            System.out.println("Максимальное количество символов '" + letter + "' в тексте: " + maxB);
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            char letter = 'c';
            int maxC = findMaxCharCount(queueC, letter);
            System.out.println("Максимальное количество символов '" + letter + "' в тексте: " + maxC);
        });
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}

