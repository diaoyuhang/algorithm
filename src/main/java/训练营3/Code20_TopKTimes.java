package 训练营3;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个由字符串组成的数组String[] strs，给定一个正数K
 * <p>
 * 返回词频最大的前K个字符串，假设结果是唯一的
 *
 * @author: Mr.diao
 * @date: 10:20 2021/5/17
 */
public class Code20_TopKTimes {

    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static void topKTimes(String[] strs, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : strs) {
            if (!map.containsKey(s)) {
                map.put(s, 0);
            }
            map.put(s, map.get(s) + 1);
        }

        //小根堆
        PriorityQueue<Node> nodes = new PriorityQueue<>((n1, n2) -> {
            return n1.times - n2.times;
        });

        for (Map.Entry<String, Integer> m : map.entrySet()) {
            if (nodes.size() < k) {
                nodes.add(new Node(m.getKey(), m.getValue()));
            } else {
                if (nodes.peek().times < m.getValue()) {
                    nodes.poll();
                    nodes.add(new Node(m.getKey(), m.getValue()));
                }
            }
        }

        while (!nodes.isEmpty()) {
            System.out.println(nodes.poll().str);
        }
    }
    public static void main(String[] args){
        String[] arr1 = { "A", "B", "A", "C", "A", "C", "B", "B", "K" };
        topKTimes(arr1,2);
    }
}
