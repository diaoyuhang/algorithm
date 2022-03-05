package basicClass.class07;

public class Code08_PaperFolding {

    public static void main(String[] args) {
        printFold(3);
    }

    public static void printFold(int num) {
        if (num < 1) {
            return;
        }
        print(num, "v");
    }

    private static void print(int num, String s) {
        if (num == 0) {
            return;
        }

        print(num - 1, "v");
        System.out.print(s);
        print(num - 1, "^");
    }
}
