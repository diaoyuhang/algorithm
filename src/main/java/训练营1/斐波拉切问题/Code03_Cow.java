package 训练营1.斐波拉切问题;

/**
 * 第一年农场有1只成熟的母牛A，往后的每年：
 * 1）每一只成熟的母牛都会生一只母牛
 * 2）每一只新出生的母牛都在出生的第三年成熟
 * 3）每一只母牛永远不会死
 * 返回N年后牛的数量
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 19:54 2021/2/28
 */
public class Code03_Cow {

    public static void main(String[] args) {
        System.out.println(function(5));
    }

    /* *
     * 1 2 3 4 6 9
     * F(N)=F(N-1)+F(N-3)
     * @Return int
     */
    public static int function(int p) {
        if (p < 5) {
            return p;
        }
        int[][] arr = {{3, 2, 1}};
        int[][] basic = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(basic, p - 3);

        int[][] ans = multiMatrix(arr, res);
        return ans[0][0]+ans[0][2];
    }

    public static int[][] matrixPower(int[][] basic, int p) {
        int[][] res = new int[basic.length][basic[0].length];
        //单位矩阵
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
