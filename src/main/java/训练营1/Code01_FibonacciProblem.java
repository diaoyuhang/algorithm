package 训练营1;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 16:40 2021/2/27
 */
public class Code01_FibonacciProblem {

    public static void main(String[] args) {
        System.out.println(function(5));
    }

    public static int function(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int[][] basic = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(basic, n - 2);

        int[][] arr={{1,1}};
        int[][] ans = muliMatrix(arr, res);
        return ans[0][0]+ans[0][1];
    }

    //矩阵次方
    public static int[][] matrixPower(int[][] basic, int p) {
        int[][] res = new int[basic.length][basic[0].length];

        //设置成单位矩阵
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] temp = basic; //basic 的1次方
        //p右移
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, temp);
            }
            //
            temp = muliMatrix(temp, temp);
        }

        return res;
    }

    //两矩阵相乘
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m2[0].length];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return result;
    }
}
