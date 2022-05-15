package TrainingCamp1.class07;

public class Code02_FallingSquares {

    public static class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int N = size + 1;
            max = new int[N];
            change = new int[N << 1];
            update = new boolean[N << 1];
        }

        public void update(int L, int R, int l, int r, int rt, int num) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = num;
                max[rt] = num;
                return;
            }

            int mid = (l + r) >> 1;
            pushDown(rt, (mid - l + 1), (r - mid));

            if (L <= mid) {
                update(L, R, l, mid, rt, num);
            }
            if (mid <= R) {
                update(L, R, mid + 1, r, rt, num);
            }
            pushUp(rt);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt] = false;
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;

                max[rt << 1] = change[rt];
                max[rt << 1 | 1] = change[rt];
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                change[rt] = 0;
            }
        }
    }
}
