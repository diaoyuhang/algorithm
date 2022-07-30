package TrainingCamp3.class05;

import java.util.*;

public class Code05_WordMinPaths {
    public static List<List<String>> findMinPaths(String start, String to, List<String> list) {
        list.add(start);
        HashMap<String, List<String>> nexts = getNexts(list);
        HashMap<String, Integer> distances = getDistances(start, nexts);
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start, to, nexts, distances, pathList, res);

        return res;
    }

    private static void getShortestPaths(String start, String to, HashMap<String, List<String>> nexts, HashMap<String, Integer> distances, LinkedList<String> pathList, List<List<String>> res) {
        pathList.add(start);
        if (start.equals(to)) {
            res.add(new ArrayList<>(pathList));
        } else {
            List<String> nextStrs = nexts.get(start);
            for (String ns : nextStrs) {
                if (distances.get(ns) == distances.get(start) + 1) {
                    getShortestPaths(ns, to, nexts, distances, pathList, res);
                }
            }
        }
        pathList.pollLast();
    }

    /**
     * start变到每个字符串需要多少步
     * @param start
     * @param nexts
     * @return
     */
    private static HashMap<String, Integer> getDistances(String start, HashMap<String, List<String>> nexts) {
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            for (String str : nexts.get(cur)) {

                if (!set.contains(str)) {
                    distances.put(str, distances.get(cur) + 1);
                    queue.add(str);
                    set.add(str);
                }
            }
        }
        return distances;
    }

    /**
     * 获取每个字符串，每次只变动一个字符的所有可能字符串
     *
     * @param list
     * @return
     */
    private static HashMap<String, List<String>> getNexts(List<String> list) {
        HashSet<String> set = new HashSet<>(list);
        HashMap<String, List<String>> nexts = new HashMap<>();
        for (String str : list) {
            nexts.put(str, getNext(str, set));
        }
        return nexts;
    }

    private static List<String> getNext(String str, HashSet<String> set) {
        char[] chars = str.toCharArray();
        ArrayList<String> next = new ArrayList<>();

        for (int i = 'a'; i <= 'z'; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] != i) {
                    int temp = chars[j];
                    chars[j] = (char) i;
                    if (set.contains(String.valueOf(chars))) {
                        next.add(String.valueOf(chars));
                    }
                    chars[j] = (char) temp;
                }
            }
        }
        return next;
    }
}
