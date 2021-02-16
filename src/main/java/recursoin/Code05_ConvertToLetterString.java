package recursoin;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 20:11 2021/2/16
 */
public class Code05_ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    private static int process(char[] chars, int i) {
        if (i >= chars.length) {
            return 1;
        }
        if (chars[i] == '0') {
            return 0;
        }
        int sum = 0;
        if (chars[i] == '2') {
            if (i + 1 < chars.length && Integer.valueOf(chars[i + 1]) <= 6) {
                sum += process(chars, i + 2);
            }
        }
        if (chars[i] == '1') {
            if (i + 1 < chars.length) {
                sum += process(chars, i + 2);
            }
        }
        sum += process(chars, i + 1);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(number("1023456789"));
    }

}
