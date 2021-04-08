package 训练营2.打表法;

/**
 * 旋转矩阵
 *
 * @author: Mr.diao
 * @date: 15:52 2021/4/8
 */
public class Code06_RotateMatrix {

    public static void rotate(int[][] matrix) {
        int ax = 0;
        int ay = 0;
        int bx = matrix[0].length-1;
        int by = matrix.length-1;
        while (ax < bx) {
            rotateEdge(matrix, ax++, ay++, bx--, by--);
        }
    }

    private static void rotateEdge(int[][] matrix, int ax, int ay, int bx, int by) {
        int temp = 0;
        for (int i = 0; i <= by - ay; i++) {
            temp = matrix[ay][ax + i];
            matrix[ay][ax + i] = matrix[by-i][ax];
            matrix[by-i][ax] = matrix[by][bx - i];
            matrix[by][bx - i] = matrix[ay + i][bx];
            matrix[ay + i][bx] = temp;
        }

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
