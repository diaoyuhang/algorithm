package 训练营3;

/**
 * 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删 除和替换一个字符的代价，返回将str1编辑成str2的最小代价。
 *
 * @author: Mr.diao
 * @date: 9:31 2021/6/16
 */
public class Code29_EditCost {

    public static Integer minCost1(String str1, String str2, Integer ic, Integer dc, Integer rc) {
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int column = ch2.length + 1;
        int row = ch1.length + 1;

        int[][] dp = new int[row][column];
        for (int i = 1; i < column; i++) {
            dp[0][i] = ic * i;
        }

        for (int i = 1; i < row; i++) {
            dp[i][0] = dc * i;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }

                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[row - 1][column - 1];
    }

    public static void main(String[] args){
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
//        System.out.println(minCost2(str1, str2, 5, 3, 2));
    }
}
