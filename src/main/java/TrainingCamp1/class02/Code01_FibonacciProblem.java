package TrainingCamp1.class02;

public class Code01_FibonacciProblem {
    public static void main(String[] args) {
        System.out.println(fibonacci(8));
    }
    public static int fibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int[][] base = {{1, 1}, {1, 0}};
        int[][] result = matrixPower(base, n - 2);
        return result[0][0] + result[1][0];
    }

    private static int[][] matrixPower(int[][] base, int power) {
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] temp = base;
        for (; power > 0; power >>= 1) {
            if ((power & 1) != 0) {
                res = muliMatrix(res, temp);
            }
            temp = muliMatrix(temp, temp);
        }
        return res;
    }

    private static int[][] muliMatrix(int[][] temp1, int[][] temp2) {
        int[][] res = new int[temp1.length][temp1[0].length];

        for (int i = 0; i < temp1.length; i++) {
            for (int j = 0; j < temp2[0].length; j++) {
                for (int k = 0; k < temp1.length; k++) {
                    res[i][j] += temp1[i][k] * temp2[k][j];
                }
            }
        }

        return res;
    }
}
