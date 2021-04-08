package 训练营2.打表法;

/**
 * 旋转打印矩阵
 *
 * @author: Mr.diao
 * @date: 10:19 2021/4/8
 */
public class Code05_PrintMatrixSpiralOrder {
    public static void spiralOrderPrint(int[][] matrix) {
        int ax = 0;
        int ay = 0;
        int by = matrix.length - 1;
        int bx = matrix[0].length - 1;

        while (ax <= bx && ay <= by) {
            print(matrix, ax++, ay++, bx--, by--);
        }
    }

    private static void print(int[][] matrix, int ax, int ay, int bx, int by) {
        if (ax == bx) {
            for (int i = ay; i <= by; i++) {
                System.out.println(matrix[i][ax]);
            }
        } else {
            int curx = ax;
            int cury = ay;
            while (curx != bx) {
                System.out.println(matrix[cury][curx++]);
            }
            while (cury != by) {
                System.out.println(matrix[cury++][curx]);
            }
            while (curx != ax) {
                System.out.println(matrix[cury][curx--]);
            }
            while (cury != ay) {
                System.out.println(matrix[cury--][curx]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1}, {5}, {9}};
        spiralOrderPrint(matrix);
    }
}
