package dynamicprogram;

/**
 * @author: Mr.diao
 * @date: 15:22 2021/2/22
 */
public class Code04_CardsInLine {
    public static void main(String[] args) {
        int[] arr = {2, 100, 99, 0};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }

    public static int win1(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        //取先手和后手的最大值
        return Math.max(first(cards, 0, cards.length - 1), second(cards, 0, cards.length - 1));
    }

    //先手取得值影响后手取得值
    private static int first(int[] cards, int l, int r) {
        if (l == r) {
            return cards[l];
        }

        return Math.max(cards[l] + second(cards, l + 1, r), cards[r] + second(cards, l, r - 1));
    }

    //后手取得值影响先手取值
    private static int second(int[] cards, int l, int r) {
        if (l == r) {
            return 0;
        }
        return Math.min(first(cards, l + 1, r), first(cards, l, r - 1));
    }

    public static int win2(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        int length = cards.length;

        int[][] first = new int[length][length];
        int[][] second = new int[length][length];

        for (int i = 0; i < length; i++) {
            first[i][i] = cards[i];
        }

        for (int index = 1; index < length; index++) {
            int l = 0;
            int r = index;

            while (l < length && r < length) {
                first[l][r] = Math.max(cards[l] + second[l + 1][r], cards[r] + second[l][r - 1]);
                second[l][r] = Math.min(first[l + 1][r], first[l][r - 1]);
                l++;
                r++;
            }
        }

        return Math.max(first[0][length - 1], second[0][length - 1]);
    }
}
