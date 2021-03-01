package 训练营1.斐波拉切问题;

/**
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 * 如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
 * 返回有多少达标的字符串
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 20:58 2021/2/28
 */
public class Code04_ZeroLeftOneStringNumber {

    public static void main(String[] args) {
        System.out.println(function(4));
    }

    /* *
     * 第一位一定是1，实际是求i-1
     *  f(5)=f(5-2)+f(5-1)
     * 1    01?
     * 当第二位为0时，第三位只能填1，直接跳到第四位,求后面达标的可能性
     * 1    1?
     * 当第二位为1时，第二位可填0，1
     */
    public static int function(int n) {
        int[][] basic = {{1, 1}, {1, 0}};

        int[][] res = matrixPower(basic, n - 2);
        int[][] arr = {{2, 1}};
        int[][] ans = multiMatrix(arr, res);
        return ans[0][0] + ans[0][1];
    }

    //矩阵次方
    public static int[][] matrixPower(int[][] basic, int p) {
        int[][] res = new int[basic.length][basic[0].length];

        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, basic);
            }
            basic = multiMatrix(basic, basic);
        }

        return res;
    }

    //矩阵相乘
    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return res;
    }
}
