package TrainingCamp3.class04;

import java.util.Arrays;
import java.util.Comparator;

public class Code05_EnvelopesProblem {

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


    public static int getSortedEnvelopes(Envelope[] envelopes) {
        Arrays.sort(envelopes, new EnvelopeComparator());
        int[] ends = new int[envelopes.length];
        ends[0] = envelopes[0].h;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < envelopes.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (envelopes[i].h > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            right = Math.max(right, l);
            ends[l] = envelopes[i].h;
        }

        return right + 1;
    }
}
