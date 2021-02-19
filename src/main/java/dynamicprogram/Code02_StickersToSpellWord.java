package dynamicprogram;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 *
 * @author: Mr.diao
 * @date: 16:52 2021/2/19
 */
public class Code02_StickersToSpellWord {
    public static void main(String[] args) {

    }

    public static int minStickers1(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }
        int n = stickers.length;
        int[][] map = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                map[i][c - 'a']++;
            }
        }

        return 0;
    }
}
