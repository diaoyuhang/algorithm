package dynamicprogram;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: Mr.diao
 * @date: 15:53 2021/2/23
 */
public class Code06_Coffee {
    public static void main(String[] args) {
        int[] test = {1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15};
        int a1 = 3;
        int b1 = 10;
        System.out.println(process1(test, a1, b1, 0, 0));
        System.out.println(dpWay(test, a1, b1));
    }

    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    public static int way1(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        PriorityQueue<Machine> pq = new PriorityQueue<>((m1, m2) -> (m1.timePoint + m1.workTime) - (m2.timePoint + m2.workTime));
        for (int value : arr) {
            pq.add(new Machine(0, value));
        }
        //每个杯子喝完的时间点
        int[] endPoint = new int[arr.length];

        int index = 0;

        while (pq.isEmpty()) {
            Machine poll = pq.poll();
            poll.timePoint += poll.workTime;
            endPoint[index] = poll.timePoint;
            index++;
        }

        return process1(endPoint, a, b, 0, 0);
    }

    private static int process1(int[] endPoint, int a, int b, int index, int washTime) {
        if (index == endPoint.length - 1) {
            //从洗杯子的结束时间和挥发杯子的结束时间中选取最小的
            return Math.min(Math.max(washTime, endPoint[index]) + a, endPoint[index] + b);
        }
        //得到index杯子洗完的时间点
        int wash = Math.max(washTime, endPoint[index]) + a;
        //index之后的杯子变干净的最早时间
        int next1 = process1(endPoint, a, b, index + 1, wash);
        //求出杯子全部变干净的最后时间点
        int ans1 = Math.max(wash, next1);

        //得到一个杯子挥发结束的时间点
        int huiFa = endPoint[index] + b;
        int next2 = process1(endPoint, a, b, index + 1, washTime);
        int ans2 = Math.max(huiFa, next2);

        return Math.min(ans1, ans2);
    }

    public static int dpWay(int[] endPoint, int a, int b) {
        if (a >= b) {
            return endPoint[endPoint.length - 1] + b;
        }

        int limit = 0;//咖啡机可以洗杯子的时间点
        for (int i = 0; i < endPoint.length; i++) {
            //算出最后一个可以洗杯子的时间
            limit = Math.max(limit, endPoint[i]) + a;
        }
        int[][] dp = new int[endPoint.length][limit + 1];
        int N = endPoint.length;
        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[N - 1][washLine] = Math.min(Math.max(washLine, endPoint[N - 1]) + a, endPoint[N - 1] + b);
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int washLine = 0; washLine <= limit; washLine++) {

                int ans1 = Integer.MAX_VALUE;
                //选择清洗
                int wash = Math.max(washLine, endPoint[i]) + a;
                if (wash <= limit) {
                    int next1 = dp[i + 1][wash];
                    ans1 = Math.max(wash, next1);
                }
                //选择挥发
                int huiFa = endPoint[i] + b;
                int next2 = dp[i + 1][washLine];
                int ans2 = Math.max(huiFa, next2);

                dp[i][washLine] = Math.min(ans1, ans2);
            }
        }

        //得到一个杯子洗完的时间点
//        int wash = Math.max(washTime, endPoint[index]) + a;
//        int next1 = process1(endPoint, a, b, index + 1, wash);
//
//        int ans1 = Math.max(wash, next1);
//        //得到一个杯子挥发结束的时间点
//        int huiFa = endPoint[index] + b;
//        int next2 = process1(endPoint, a, b, index + 1, washTime);
//        int ans2 = Math.max(huiFa, next2);

        return dp[0][0];
    }
}
