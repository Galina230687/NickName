import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = Utils.generateText("abc", 3 + random.nextInt(3));
        }

        Thread poliandrome = new Thread(() -> {
            for (String text : texts) {
                if (Utils.isPoliandrome(text) && !Utils.isSameChar(text)) {
                    Utils.incrementCounter(text.length());

                }
            }
        });
        poliandrome.start();


        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (Utils.isSameChar(text)) {
                    Utils.incrementCounter(text.length());
                }
            }

        });
        sameChar.start();

        Thread ascendingOrder = new Thread(() -> {
            for (String text : texts) {
                if (Utils.isSameChar(text) && Utils.isAscendingOrder(text)) {
                    Utils.incrementCounter(text.length());
                }
            }
        });
        ascendingOrder.start();

        sameChar.join();
        ascendingOrder.join();
        poliandrome.join();

        System.out.println("Красивых слов длиной 3 -> " + Utils.counter3);
        System.out.println("Красивых слов длиной 4 -> " + Utils.counter4);
        System.out.println("Красивых слов длиной 5 -> " + Utils.counter5);

    }
}

