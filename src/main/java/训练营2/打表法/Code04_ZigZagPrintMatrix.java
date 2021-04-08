package 训练营2.打表法;

/**
 * 之字形打印矩阵
 *
 * @author: Mr.diao
 * @date: 9:35 2021/4/8
 */
public class Code04_ZigZagPrintMatrix {
    public static void print(int[][] matrix) {
        int length = matrix[0].length - 1;
        int width = matrix.length - 1;
        int[] indexA = {0, 0};
        int[] indexB = {0, 0};
        int direction = 1;
        while (indexA[1] <= length && indexA[0] <= width) {
            if (direction == 1) {
                int x = indexB[1];
                int y = indexB[0];

                for (; x <= indexA[1] && y >= indexA[0]; x++, y--) {
                    System.out.println(matrix[y][x]);
                }
                direction = 2;
            } else {
                int x = indexA[1];
                int y = indexA[0];
                for (; x >= indexB[1] && y <= indexB[0]; x--, y++) {
                    System.out.println(matrix[y][x]);
                }
                direction = 1;
            }
            if (indexA[1] < length) {
                indexA[1]++;
            } else {
                indexA[0]++;
            }
            if (indexB[0] < width) {
                indexB[0]++;
            } else {
                indexB[1]++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        print(matrix);
    }
}
