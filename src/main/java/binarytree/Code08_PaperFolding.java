package binarytree;

/**
 * @author: Mr.diao
 * @date: 11:02 2021/1/12
 */
public class Code08_PaperFolding {
    public static void main(String[] args) {
        printAllFolds(4);
    }

    public static void printAllFolds(int n) {
        printFold(1,n,true);
    }

    public static void printFold(int i, int n, boolean flag) {
        if (i > n) {
            return;
        }
        printFold(i + 1, n, false);
        System.out.println(flag ? "down" : "up");
        printFold(i + 1, n, true);
    }
}
