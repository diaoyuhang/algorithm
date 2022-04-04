package basicClass.class12;

import java.util.HashMap;

public class Code02_StickersToSpellWord {

    public static int minStickers(String[] stickers, String target) {

        HashMap<String, Integer> dp = new HashMap<>();
        int[][] map = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] cs = stickers[i].toCharArray();
            for (char c : cs) {
                map[i][c - 'a']++;
            }
        }
        dp.put("", 0);
        return process(dp, map, target);
    }

    private static int process(HashMap<String, Integer> dp, int[][] map, String target) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }

        int anx = Integer.MAX_VALUE;
        int[] targetChar = new int[26];
        char[] targets = target.toCharArray();
        for (char c : targets) {
            targetChar[c - 'a']++;
        }

        for (int i = 0; i < map.length; i++) {
            if (map[i][targets[0] - 'a'] == 0) {
                continue;
            }

            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (targets[j] > 0) {
                    //拼接剩余字符
                    int rest = Math.max(targets[j] - map[i][j], 0);
                    for (int n = 0; n < rest; n++) {
                        builder.append((char) ('a' + j));
                    }
                }
            }
            int temp = process(dp, map, builder.toString());
            if (temp != -1) {
                anx = Math.min(anx, 1 + temp);
            }
        }
        dp.put(target, anx == Integer.MAX_VALUE ? -1 : anx);
        return dp.get(target);
    }

}
