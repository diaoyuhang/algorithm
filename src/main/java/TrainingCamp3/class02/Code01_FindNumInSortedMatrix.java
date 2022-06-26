package TrainingCamp3.class02;

public class Code01_FindNumInSortedMatrix {

    public static boolean isContains(int[][] matrix, int num) {

        int row = 0;
        int column = matrix[0].length - 1;

        while (row < matrix.length) {
            if (matrix[row][column] < num) {
                row++;
            } else {

                while (column >= 0) {

                    if (matrix[row][column] > num) {
                        column--;
                    } else if (matrix[row][column] == num) {
                        return true;
                    } else {
                        return false;
                    }
                }

            }
        }
        return false;
    }
}
