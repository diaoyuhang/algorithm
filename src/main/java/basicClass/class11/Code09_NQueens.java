package basicClass.class11;

/**
 * @author: diaoyuhang
 * @date 2022/3/29
 * N皇后问题
 */
public class Code09_NQueens {

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println(nQueens2(32));
        System.out.println(System.currentTimeMillis() - begin);

    }

    public static int nQueens1(int num) {
        if (num <= 0) {
            return 0;
        }
        Integer[] arr = new Integer[num];
        return process1(arr, num, 0);
    }

    private static int process1(Integer[] arr, int allRows, int row) {
        if (row == allRows) {
            return 1;
        }
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            if (isValid(arr, row, i)) {
                arr[row] = i;
                res += process1(arr, allRows, row + 1);
            }
        }
        return res;
    }

    /**
     * 验证第row行的第column列是否与之前的皇后有冲突
     *
     * @param row
     * @param column
     * @return
     */
    private static boolean isValid(Integer[] arr, int row, int column) {
        for (int i = 0; i < row; i++) {
            if (arr[i] == column || Math.abs(i - row) == Math.abs(arr[i] - column)) {
                return false;
            }
        }
        return true;
    }

    public static int nQueens2(int num) {
        if (num <= 0) {
            return 0;
        }

        int limit = num == 32 ? -1 : ((1 << num) - 1);
        return process2(limit, 0, 0, 0);
    }

    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        int res = 0;
        //当前行所能摆放的位置，1代表可选位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        while (pos != 0) {
            //取出pos的最右边的1
            mostRightOne = pos & (~pos + 1);
            res += process2(limit, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
            pos -= mostRightOne;
        }

        return res;
    }
}
