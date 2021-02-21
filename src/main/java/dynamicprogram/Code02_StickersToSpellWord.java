package dynamicprogram;

import java.util.HashMap;

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
        String[] stickers = {"ababac", "cdcccd", "bdbac"};
        String target = "ababa";
        System.out.println(minStickers1(stickers, target));
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
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(dp, map, target);
    }

    private static int process(HashMap<String, Integer> dp, int[][] map, String target) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }

        char[] chars = target.toCharArray();
        int[] tmap = new int[26];
        //将目标字符串转换成词频的形式
        for (char c : chars) {
            tmap[c - 'a']++;
        }

        int ans = Integer.MAX_VALUE;
        //遍历每一张贴纸
        for (int i = 0; i < map.length; i++) {
            if (map[i][chars[0]-'a']==0){
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder();
            //顺序遍历26个字母
            for (int j = 0; j < 26; j++) {
                //如果当前位置的字母数量>0,用当前的贴纸来处理这个字母
                if (tmap[j] > 0) {
                    for (int num = 0; num < Math.max(0, tmap[j]-map[i][j]); num++) {
                        //将未处理的剩余字符拼接起来
                        stringBuilder.append((char) ('a' + j));
                    }
                }
            }
            String t = stringBuilder.toString();
            int result = process(dp, map, t);
            if (result != -1) {
                ans = Math.min(ans, 1 + result);
            }
        }
        dp.put(target, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(target);
    }

}
