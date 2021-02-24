package dynamicprogram;

/**
 * @author: Mr.diao
 * @date: 16:25 2021/2/24
 */
public class Code08_MinPathSum {
    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1},
                {8, 8, 4, 0}};
        printMatrix(m);
        System.out.println(way1(m));
        System.out.println(dpWay(m));
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int way1(int[][] m) {
        return process(m, 0, 0);
    }

    private static int process(int[][] m, int y, int x) {
        if (y >= m.length || y < 0 || x >= m[0].length || x < 0) {
            return -1;
        }
        if (y == m.length - 1 && x == m[0].length - 1) {
            return m[y][x];
        }

//        int left = process(m, y, x + 1);
        int down = process(m, y + 1, x);
        int right = process(m, y, x + 1);
//        int up = process(m, y - 1, x);

        int ans = Integer.MAX_VALUE;
//        if (left != -1) {
//            ans = Math.min(ans, m[y][x] + left);
//        }
        if (down != -1) {
            ans = Math.min(ans, m[y][x] + down);
        }
        if (right != -1) {
            ans = Math.min(ans, m[y][x] + right);
        }
//        if (up != -1) {
//            ans = Math.min(ans, m[y][x] + up);
//        }

        return ans;
    }

    public static int dpWay(int[][] m) {
        int[][] dp = new int[m.length][m[0].length];
        int y = m.length - 1;
        int x = m[0].length - 1;
        dp[y][x] = m[y][x];

        for (int i = x - 1; i >= 0; i--) {
            dp[y][i] = m[y][i] + m[y][i + 1];
        }

        for (int j = y - 1; j >= 0; j--) {
            dp[j][x] = m[j][x] + m[j + 1][x];
        }

        for (int i = y - 1; i >= 0; i--) {
            for (int j = x - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + m[i][j];
            }
        }
        return dp[0][0];
    }
}
