package basicClass.class11;

import java.text.MessageFormat;

public class Code01_Hanoi {
    public static void main(String[] args) {
        hanoi(20);
    }
    public static void hanoi(int level) {
        String from = "left";
        String to = "right";
        String other = "mid";

        printHanoi(level, from, to, other);
    }

    private static void printHanoi(int level, String from, String to, String other) {
        if (level == 1) {
            System.out.println(MessageFormat.format("第{0}块，从{1}移动到{2}", level, from, to));
        } else {
            printHanoi(level - 1, from, other, to);
            System.out.println(MessageFormat.format("第{0}块，从{1}移动到{2}", level, from, to));
            printHanoi(level - 1, other, to, from);
        }
    }

}
