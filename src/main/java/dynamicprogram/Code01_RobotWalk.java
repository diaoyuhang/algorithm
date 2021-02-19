package dynamicprogram;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 *
 * @author: Mr.diao
 * @date: 11:12 2021/2/19
 */
public class Code01_RobotWalk {

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
    }

    public static int ways1(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        return process1(N, M, K, P);
    }

    private static int process1(int n, int m, int k, int p) {
        if (k == 0) {
            return m == p ? 1 : 0;
        }
        int res = 0;
        //当m来到1的位置的时候，只能往右走
        if (m == 1) {
            res += process1(n, m + 1, k - 1, p);
        }
        //当m来到n的位置，只能往左走
        else if (m == n) {
            res += process1(n, m - 1, k - 1, p);
        } else {
            res += process1(n, m - 1, k - 1, p);
            res += process1(n, m + 1, k - 1, p);
        }

        return res;
    }

    //变化的是M和K
    private static int ways2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }

        int[][] dp = new int[N + 1][K + 1];
        dp[P][0] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    dp[j][i] += dp[2][i - 1];
                } else if (j == N) {
                    dp[j][i] += dp[N - 1][i - 1];
                } else {
                    dp[j][i] = dp[j + 1][i - 1] + dp[j - 1][i - 1];
                }
            }
        }
        return dp[M][K];
    }
}
