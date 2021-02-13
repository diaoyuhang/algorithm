package greedy;

import java.util.HashSet;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 10:54 2021/2/13
 */
public class Code02_Light {

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }

    public static int minLight1(String road) {
        if (road == null || "".equals(road)) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<Integer>());
    }

    private static int process(char[] chars, int index, HashSet<Integer> lights) {
        if (index == chars.length) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != 'X') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            int no = process(chars, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (chars[index] == '.') {
                lights.add(index);
                yes = process(chars, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(yes, no);
        }
    }

    public static int minLight2(String road) {
        char[] chars = road.toCharArray();
        int light = 0;
        int index = 0;
        while (index < chars.length) {
            if (chars[index] == 'X') {
                index++;
            } else {
                light++;
                if (index + 1 == chars.length) {
                    break;
                } else {
                    //判断下一个是不是‘.’
                    if (chars[index + 1] == 'X') {
                        index += 2;
                    } else {
                        index += 3;
                    }
                }
            }
        }

        return light;
    }
}
