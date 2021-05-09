package 训练营3;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 每种工作有难度和报酬，规定如下
 * class Job {
 * public int money;// 该工作的报酬
 * public int hard; // 该工作的难度
 * }
 * 给定一个Job类型的数组jobarr，表示所有岗位，每个岗位都可以提供任意份工作
 * 选工作的标准是在难度不超过自身能力值的情况下，选择报酬最高的岗位
 * 给定一个int类型的数组arr，表示所有人的能力
 * 返回int类型的数组，表示每个人按照标准选工作后所能获得的最高报酬
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 17:07 2021/5/9
 */
public class Code15_ChooseWork {
    public static class Job {
        public int money;// 该工作的报酬
        public int hard; // 该工作的难度
    }

    public static int[] getMoneys(Job[] jobs, int[] ability) {
        Arrays.sort(jobs, (o1, o2) ->
                o1.hard != o2.hard ? o1.hard - o2.hard : o1.money - o2.money);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Job pre = jobs[0];
        map.put(pre.hard, pre.money);

        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i].money > pre.money) {
                pre = jobs[i];
                map.put(pre.hard, pre.money);
            }
        }
        int[] moneys = new int[ability.length];

        for (int i = 0; i < ability.length; i++) {
            Integer key = map.floorKey(ability[i]);
            moneys[i] = key == null ? 0 : map.get(key);
        }
        return moneys;
    }
}
