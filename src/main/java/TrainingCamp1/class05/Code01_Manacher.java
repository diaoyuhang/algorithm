package TrainingCamp1.class05;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Code01_Manacher {

    public static void main(String[] args) {

    }

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = manacherString(s);
        int[] pArr = new int[chars.length];

        int c = -1;
        int r = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {
            pArr[i] = r > i ? Math.min(pArr[2 * c - i], r - i) : 1;
            while (i + pArr[i] < chars.length && i - pArr[i] >= 0) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > r) {
                c = i;
                r = i + pArr[i];
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    private static char[] manacherString(String s) {
        char[] chars = new char[s.length() * 2 + 1];
        chars[0] = '#';
        char[] sChars = s.toCharArray();
        int index = 1;
        for (char c : sChars) {
            chars[index++] = c;
            chars[index++] = '#';
        }
        return chars;
    }
}
