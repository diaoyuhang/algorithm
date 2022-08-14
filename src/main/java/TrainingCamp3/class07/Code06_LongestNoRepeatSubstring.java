package TrainingCamp3.class07;

public class Code06_LongestNoRepeatSubstring {

    public static int maxUnique(String str) {
        char[] chars = str.toCharArray();
        //记录上次字符出现的位置
        int[] map = new int[255];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }

        int len = 0;
        int pre = -1;
        int cur = -1;
        for (int i = 0; i < chars.length; i++) {
            pre = Math.max(pre, map[chars[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chars[i]] = i;
        }
        return len;
    }
}
