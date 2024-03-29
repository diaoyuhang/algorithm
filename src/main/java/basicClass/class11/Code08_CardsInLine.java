package basicClass.class11;

/**
 * @author: diaoyuhang
 * @date 2022/3/28
 */
public class Code08_CardsInLine {

    public static int cardsInLine(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        //先手
        int result1 = first(cards, 0, cards.length - 1);
        //后手
        int result2 = second(cards, 0, cards.length - 1);
        return Math.max(result1, result2);
    }

    private static int first(int[] cards, int left, int right) {
        if (left == right) {
            return cards[left];
        }
        int res1 = cards[left] + second(cards, left + 1, right);
        int res2 = cards[right] + second(cards, left, right - 1);
        return Math.max(res1, res2);
    }

    private static int second(int[] cards, int left, int right) {
        if (left == right) {
            return 0;
        }
        int res1 = first(cards, left + 1, right);
        int res2 = first(cards, left, right - 1);
        return Math.min(res1, res2);
    }

    public static int dpWay(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }

        int[][] firstDp = new int[cards.length][cards.length];
        int[][] secondDp = new int[cards.length][cards.length];

        for (int i = 0; i < firstDp.length; i++) {
            firstDp[i][i] = cards[i];
            secondDp[i][i] = 0;
        }

        for (int right = 1; right < cards.length; right++) {
            for (int left = right - 1; left >= 0; left--) {
                firstDp[left][right] = Math.max(cards[left] + secondDp[left + 1][right], cards[right] + secondDp[left][right - 1]);
                secondDp[left][right] = Math.min(firstDp[left + 1][right], firstDp[left][right - 1]);
            }
        }

        return Math.max(firstDp[0][cards.length - 1], secondDp[0][cards.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 1, 234, 345, 456, 456, 435, 236, 456, 3521};
        System.out.println(cardsInLine(arr));
        System.out.println(dpWay(arr));

    }
}
