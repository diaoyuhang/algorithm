package 训练营3;

/**
 * 给定一个整型矩阵，返回子矩阵的最大累计和。
 *
 * @author: Mr.diao
 * @date: 19:50 2021/5/31
 */
public class Code28_MaxSumMatrix {

    public static int maxSum(int[][] matrix) {

        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;

        for (int i = 0; i < matrix.length; i++) {
            s = new int[matrix[i].length];
            for (int j = i; j < matrix.length; j++) {
                cur = 0;
                for (int k = 0; k < s.length; k++) {
                    s[k] += matrix[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = Math.max(cur, 0);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        System.out.println(maxSum(matrix));

    }
}
