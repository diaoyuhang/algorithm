package dynamicprogram;

/**
 * @author: Mr.diao
 * @date: 14:52 2021/2/24
 */
public class Code07_HorseJump {

    public static void main(String[] args) {
        int x = 7;
        int y = 6;
        int step = 11;
        System.out.println(way1(x, y, step));
        System.out.println(dpWay(x, y, step));
    }

    //x,y为最终来到位置的坐标
    public static int way1(int x, int y, int k) {

        return process(x, y, k, 0, 0);
    }

    //a,为马的横坐标，b为纵坐标
    private static int process(int x, int y, int k, int a, int b) {
        if (a < 0 || a >= 9 || b < 0 || b >= 10) {
            return 0;
        }
        if (k == 0) {
            return a == x && b == y ? 1 : 0;
        }

        return process(x, y, k - 1, a - 1, b + 2)
                + process(x, y, k - 1, a + 1, b + 2)
                + process(x, y, k - 1, a + 2, b + 1)
                + process(x, y, k - 1, a + 2, b - 1)
                + process(x, y, k - 1, a + 1, b - 2)
                + process(x, y, k - 1, a - 1, b - 2)
                + process(x, y, k - 1, a - 2, b - 1)
                + process(x, y, k - 1, a - 2, b + 1);
    }

    public static int dpWay(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[y][x][0] = 1;
        for (int n = 1; n <= k; n++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {

                    dp[j][i][n] = getValue(dp, j + 2, i - 1, n - 1)
                            + getValue(dp, j + 2, i + 1, n - 1)
                            + getValue(dp, j + 1, i + 2, n - 1)
                            + getValue(dp, j - 1, i + 2, n - 1)
                            + getValue(dp, j - 2, i + 1, n - 1)
                            + getValue(dp, j - 2, i - 1, n - 1)
                            + getValue(dp, j - 1, i - 2, n - 1)
                            + getValue(dp, j + 1, i - 2, n - 1);

                }
            }
        }

        return dp[0][0][k];
    }

    public static int getValue(int[][][] dp, int j, int i, int step) {
        if (i < 0 || i >= 9 || j < 0 || j >= 10) {
            return 0;
        }
        return dp[j][i][step];
    }
}
