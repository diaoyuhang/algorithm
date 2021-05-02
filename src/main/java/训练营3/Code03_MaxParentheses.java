package 训练营3;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 返回一个括号字符串中，最长的括号有效子串的长度
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 9:43 2021/5/2
 */
public class Code03_MaxParentheses {

    public static int maxLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[str.length()];
        int res = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                //找到与当前‘）’匹配的左括号的位置
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {

                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return 0;
    }
}
