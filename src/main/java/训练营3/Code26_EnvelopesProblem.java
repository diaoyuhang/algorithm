package 训练营3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 每个信封都有长和宽两个维度的数据，A信封如果想套在B信封里面，A信封必须在长和宽上都小于B信封才行。
 * 如果给你一批信封，返回最大的嵌套层数
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 21:53 2021/5/30
 */
public class Code26_EnvelopesProblem {
    public static class Envelope {
        public int l;
        public int h;

        public Envelope(int weight, int hight) {
            l = weight;
            h = hight;
        }
    }


    public static class EnvelopeComparator implements Comparator<Envelope> {

        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
        }
    }


    public static int maxEnvelopes(int[][] arr) {
        Envelope[] envelopes = getSortedEnvelopes(arr);
        int[] ends = new int[arr.length];
        ends[0] = envelopes[0].h;

        int l = 0;
        int r = 0;
        int right = 0;
        int mid = 0;
        for (int i = 1; i < envelopes.length; i++) {
            l = 0;
            r = right;

            while (l <= r) {
                mid = (l + r) / 2;

                if (envelopes[i].h < ends[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            right = Math.max(right, l);
            ends[l] = envelopes[i].h;
        }

        return right + 1;
    }

    private static Envelope[] getSortedEnvelopes(int[][] arr) {
        Envelope[] envelopes = new Envelope[arr.length];
        for (int i = 0; i < arr.length; i++) {
            envelopes[i] = new Envelope(arr[i][0], arr[i][1]);
        }
        Arrays.sort(envelopes, new EnvelopeComparator());

        return envelopes;
    }

    public static void main(String[] args){
        int[][] test = { { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 }, { 2, 2 }, { 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
        System.out.println(maxEnvelopes(test));
    }
}
