package TrainingCamp3.class03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Code01_ChooseWork {

    public static class Job {
        public int money;
        public int hard;

        public Job(int money, int hard) {
            this.money = money;
            this.hard = hard;
        }
    }

    public static class JobComparator implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
        }
    }


    public static int[] getMoneys(Job[] jobArr, int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Arrays.sort(jobArr, new JobComparator());
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(jobArr[0].hard, jobArr[0].money);
        Job preJob = jobArr[0];

        for (int i = 1; i < jobArr.length; i++) {
            if (jobArr[i].hard != preJob.hard && preJob.money < jobArr[i].money) {
                preJob = jobArr[i];
                treeMap.put(preJob.hard, preJob.money);
            }
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            Integer key = treeMap.floorKey(arr[i]);
            res[i] = key != null ? treeMap.get(key) : 0;
        }
        return res;
    }
}
