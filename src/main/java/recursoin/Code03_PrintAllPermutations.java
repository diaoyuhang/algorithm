package recursoin;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 16:04 2021/2/16
 */
public class Code03_PrintAllPermutations {
    public static void main(String[] args) {
        String s = "abcd";
        List<String> ans1 = permutation(s);
        for (String str : ans1) {
            System.out.println(str);
        }
//        System.out.println("=======");
//        List<String> ans2 = permutationNoRepeat(s);
//        for (String str : ans2) {
//            System.out.println(str);
//        }

    }

    public static ArrayList<String> permutation(String str) {
        char[] chars = str.toCharArray();
        ArrayList<String> result = new ArrayList<>();

        if (str == null || str.length() == 0) {
            return result;
        }

        process(chars, 0, result);
        return result;
    }

    private static void process(char[] chars, int i, ArrayList<String> result) {
        if (i == chars.length) {
            result.add(String.valueOf(chars));
        }
        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            process(chars, i + 1, result);
            swap(chars, i, j);
        }

    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
