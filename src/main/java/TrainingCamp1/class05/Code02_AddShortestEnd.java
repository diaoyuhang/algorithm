package TrainingCamp1.class05;

public class Code02_AddShortestEnd {

    public static void main(String[] args) {
        System.out.println(shortestEndStr("abcd12332"));
    }

    public static String shortestEndStr(String s) {
        int index = manacher(s);
        char[] chars = s.toCharArray();
        String res = "";
        for (int i = index; i >= 0; i--) {
            res += chars[i];
        }
        return res;
    }

    private static int manacher(String s) {
        char[] chars = manacherString(s);
        int[] pArr = new int[chars.length];

        int c = -1;
        int r = -1;
        for (int i = 0; i < chars.length; i++) {
            pArr[i] = r > i ? Math.min(r - i, pArr[2 * c - i]) : 1;
            while (i + pArr[i] < chars.length && i - pArr[i] >= 0) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] >= chars.length) {
                return s.length()-pArr[i];
            }
        }
        return s.length() - 2;
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
