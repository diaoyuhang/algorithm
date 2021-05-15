package 训练营3;

/**
 * 给定两个字符串str1和str2，求两个字符串的最长公共子串
 * <p>
 * 动态规划的空间压缩技巧！
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 17:53 2021/5/15
 */
public class Code19_LCSubstring {

    public static String lcst(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int row = 0;
        int col = chars2.length - 1;
        int max = 0;
        int end = 0;

        while (row < chars1.length) {
            int i = row;
            int j = col;
            int len = 0;
            while (i < chars1.length && j < chars2.length) {
                if (chars1[i] != chars2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
//        System.out.println(lcst1(str1, str2));
        System.out.println(lcst(str1, str2));

    }
}
