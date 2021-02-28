package 训练营1.斐波拉切问题;

/**
 * 一个人可以一次往上迈1个台阶，也可以迈2个台阶
 * 返回这个人迈上N级台阶的方法数
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 20:30 2021/2/27
 */
public class Code02_StepUpStairs {

    public static void main(String[] args) {
        System.out.println(function1(5));
    }

    /* *
     * 1    1
     * 2    2
     * 3    3
     * 4    5
     * 5    8
     */
    //    F(n)=F(n-1)+F(n-2)
    public static int function1(int n) {
        int[][] basic = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(basic, n-2);
        int[][] arr={{2,1}};

        int[][] ans = multiMatrix(arr, res);
        return ans[0][0]+ans[0][1];
    }

    //矩阵次方
    public static int[][] matrixPower(int[][] basic, int p) {
        int[][] res = new int[basic.length][basic[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        //p的二进制 比如10001011
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
