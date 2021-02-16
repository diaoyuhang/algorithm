package recursoin;

import java.util.HashSet;
import java.util.List;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 11:28 2021/2/16
 */
public class Code02_PrintAllSubSquences {
    public static void main(String[] args) {
        String test = "aacc";
        HashSet<String> ans2 = subAllNoRepeat(test);

        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }
    public static HashSet<String> subAllNoRepeat(String s) {
        char[] chars = s.toCharArray();
        HashSet<String> set = new HashSet<>();

        process(chars, set, 0, "");
        return set;
    }

    public static void process(char[] chars, HashSet<String> set, Integer index, String path) {
        if (index == chars.length) {
            set.add(path);
            return;
        }
        String no = path;
        process(chars, set, index + 1, no);
        String yes = path + chars[index];
        process(chars, set, index + 1, yes);
    }
}
