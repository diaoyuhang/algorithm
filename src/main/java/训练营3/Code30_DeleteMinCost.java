package 训练营3;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如 s1 = "abcde"，s2 = "axbc"
 * 返回1。s2删掉'x'就是s1的子串了
 *
 * @author: Mr.diao
 * @date: 9:29 2021/6/16
 */
public class Code30_DeleteMinCost {

    public static int minCost1(String s1, String s2) {
        //求出s2中所有的子序列
        char[] ch2 = s2.toCharArray();
        List<String> res = new ArrayList<>();
        process(ch2, "", res, 0);
        res.sort((o1, o2) -> o2.length() - o1.length());

        for (String str : res) {
            if (s1.indexOf(str) != -1) { //判断s2的子序列是否在s1中存在
                return s2.length() - str.length();
            }
        }
        return -1;
    }

    private static void process(char[] ch2, String path, List<String> res, int index) {
        if (index == ch2.length) {
            res.add(path);
            return;
        }
        process(ch2, path, res, index + 1);
        process(ch2, path + ch2[index], res, index + 1);
    }

    public static int minCost2(String s1, String s2) {
        char[] ch2 = s2.toCharArray();
        int ans = Integer.MAX_VALUE;
        //求出s1的所有的子串
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i + 1; j <= s1.length(); j++) {
                ans = Math.min(ans, distance(s1.substring(i, j).toCharArray(), ch2));
            }
        }
        return ans;
    }

    private static int distance(char[] ch1, char[] ch2) {
        int column = ch1.length;
        int row = ch2.length;

        int[][] dp = new int[row][column];
        dp[0][0] = ch1[0] == ch2[0] ? 0 : Integer.MAX_VALUE;

        for (int i = 1; i < column; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] !=Integer.MAX_VALUE || ch2[i] == ch1[0]) ? i : Integer.MAX_VALUE;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (ch2[i] == ch1[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[row - 1][column - 1];
    }

    // 以下的代码仅为了测试使用
    // 先生成一个随机字符串，比如 123abcd456
    // 再生根据这个字符串的随机某部分比如abcd，随机添加字符之后生成abckd
    // 生成的123abcd456和abckd都返回，这个方法就是本题的随机样本发生器
    public static String[] generateTwoStrings() {
        int len = (int) (Math.random() * 20) + 5;
        int adds = (int) (Math.random() * 5);
        char[] chas = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z'};
        int N = chas.length;
        char[] str1 = new char[len];
        for (int i = 0; i < str1.length; i++) {
            str1[i] = chas[(int) (Math.random() * N)];
        }
        int a = (int) (Math.random() * (str1.length));
        int b = (int) (Math.random() * (str1.length));
        int left = Math.min(a, b);
        int right = Math.max(a, b) + 1;
        char[] part = String.valueOf(str1).substring(left, right).toCharArray();
        char[] str2 = new char[part.length + adds];
        int count = 0;
        while (count != adds) {
            int i = (int) (Math.random() * str2.length);
            if (str2[i] == 0) {
                str2[i] = chas[(int) (Math.random() * N)];
                count++;
            }
        }
        int index = 0;
        for (int i = 0; i < str2.length; i++) {
            if (str2[i] == 0) {
                str2[i] = part[index++];
            }
        }
        return new String[]{String.valueOf(str1), String.valueOf(str2)};
    }

    public static void main(String[] args) {
        int testTime = 100;
        boolean pass = true;
        for (int i = 0; i < testTime; i++) {
            String[] test = generateTwoStrings();
            if (minCost1(test[0], test[1]) != minCost2(test[0], test[1])) {
                pass = false;
                System.out.println(test[0]);
                System.out.println(test[1]);
                System.out.println(minCost1(test[0], test[1]));
                System.out.println(minCost2(test[0], test[1]));
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }

}
