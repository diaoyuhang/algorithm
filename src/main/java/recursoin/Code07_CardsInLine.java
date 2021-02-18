package recursoin;

/**
 * @author: Mr.diao
 * @date: 9:59 2021/2/18
 */
public class Code07_CardsInLine {

    public static void main(String[] args) {
        int[] arr = {10, 9, 1};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(first(arr, 0, arr.length - 1),
                second(arr, 0, arr.length - 1));
    }

    public static int first(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }

        return Math.max(arr[l] + second(arr, l + 1, r), arr[r] + second(arr, l, r - 1));
    }

    private static int second(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        return Math.min(first(arr, l + 1, r), first(arr, l, r - 1));
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];

        for (int index = 0; index < arr.length; index++) {
            f[index][index] = arr[index];
        }

        for (int i = 1; i < n; i++) {
            int l = 0;
            int r = i;

            while (l < n && r < n) {
                f[l][r] = Math.max(arr[l] + s[l + 1][r], arr[r] + s[l][r - 1]);
                s[l][r] = Math.min(f[l + 1][r], f[l][r - 1]);
                l++;
                r++;
            }
        }

        return Math.max(f[0][n - 1], s[0][n - 1]);
    }

}
