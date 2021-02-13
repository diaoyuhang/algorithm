package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 9:16 2021/2/13
 */
public class Code01_LowestLexicography {

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        ArrayList<String> allStr = new ArrayList<>();
        HashSet<Integer> use = new HashSet<>();
        process(strs, allStr, use, "");
        String lowest = allStr.get(0);
        for (int i = 1; i < allStr.size(); i++) {
            if (allStr.get(i).compareTo(lowest) < 0) {
                lowest = allStr.get(i);
            }
        }
        return lowest;
    }

    private static void process(String[] strs, ArrayList<String> allStr, HashSet<Integer> use, String path) {
        if (use.size() == strs.length) {
            allStr.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    process(strs, allStr, use, path + strs[i]);
                    use.remove(i);
                }
            }
        }
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, (a, b) ->
                (a + b).compareTo(b + a)
        );
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
