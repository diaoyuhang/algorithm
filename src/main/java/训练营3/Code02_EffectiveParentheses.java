package 训练营3;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 * 问题一：怎么判断一个括号字符串有效？
 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 9:14 2021/5/2
 */
public class Code02_EffectiveParentheses {

    public static boolean judgmentEffective(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        int count = 0;
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                count++;
            } else {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0 ? true : false;
    }

    public static int fillIn(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        int res = 0;
        for (char c : chars) {
            if (c == '(') {
                count++;
            } else {
                count--;
                if (c < 0) {
                    res++;
                    count++;
                }
            }
        }
        return res += count;
    }
}
