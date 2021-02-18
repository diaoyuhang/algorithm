package recursoin;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 20:47 2021/2/18
 */
public class Code08_NQueens {
    public static void main(String[] args) {
        System.out.println(num1(8));
    }

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] records = new int[n];
        return process(records, 0);
    }

    private static int process(int[] records, int index) {
        if (index == records.length) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < records.length; j++) {
            //判断当前位置是否与之前摆放的有冲突
            if (isValid(records, index, j)) {
                records[index] = j;
                res += process(records, index + 1);
            }
        }
        return res;
    }

    private static boolean isValid(int[] records, int i, int j) {
        for (int index = 0; index < i; index++) {
            if (records[index] == j || Math.abs(i - index) == Math.abs(j - records[index])) {
                return false;
            }
        }
        return true;
    }
}
