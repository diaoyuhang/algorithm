package basicClass.class11;

import java.util.ArrayList;

public class Code03_PrintAllPermutations {

    public static ArrayList<String> permutation1(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process1(chs, res, 0);
        return res;
    }

    private static void process1(char[] chs, ArrayList<String> res, int index) {
        if (index == chs.length) {
            res.add(new String(chs));
            return;
        }

        for (int j = index; j < chs.length; j++) {
            swap(chs, index, j);
            process1(chs, res, j + 1);
            swap(chs, index, j);
        }
    }

    private static void swap(char[] chs, int index, int j) {
        char temp = chs[index];
        chs[index] = chs[j];
        chs[j] = temp;
    }

    public static ArrayList<String> permutation2(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process2(chs, res, 0);
        return res;
    }

    private static void process2(char[] chs, ArrayList<String> res, int index) {
        if (index == chs.length) {
            res.add(new String(chs));
            return;
        }
        boolean[] validate = new boolean[26];

        for (int j = index; j < chs.length; j++) {
            if (!validate['a' - chs[index]]) {
                validate['a' - chs[index]] = true;
                swap(chs, index, j);
                process2(chs, res, j + 1);
                swap(chs, index, j);
            }
        }
    }
}
