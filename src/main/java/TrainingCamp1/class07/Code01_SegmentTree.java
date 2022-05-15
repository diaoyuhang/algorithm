package TrainingCamp1.class07;

public class Code01_SegmentTree {

    private int MAXN;
    private int[] arr;
    private int[] sum;
    private int[] lazy;
    private int[] change;
    private boolean[] update;

    public Code01_SegmentTree(int[] origin) {
        this.MAXN = origin.length + 1;
        arr = new int[this.MAXN];

        for (int i = 1; i < arr.length; i++) {
            arr[i] = origin[i - 1];
        }
        sum = new int[this.MAXN << 1];
        lazy = new int[this.MAXN << 1];
        change = new int[this.MAXN << 1];
        update = new boolean[this.MAXN << 1];
    }

    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }

        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);

        pushUp(rt);
    }

    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    public void update(int L, int R, int l, int r, int rt, int num) {
        if (L <= l && r >= R) {
            sum[rt] = num * (r - l + 1);
            change[rt] = num;
            update[rt] = true;
            lazy[rt] = 0;
            return;
        }

        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);

        if (L <= mid) {
            update(L, R, mid + 1, r, rt << 1, num);
        }
        if (R > mid) {
            update(L, R, mid + 1, r, rt << 1 | 1, num);
        }
        pushUp(rt);
    }

    public void add(int L, int R, int l, int r, int rt, int num) {
        if (L <= l && R >= r) {
            sum[rt] += num * (r - l + 1);
            lazy[rt] += num;
            return;
        }

        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            add(L, R, l, mid, rt << 1, num);
        }
        if (R > mid) {
            add(L, R, mid + 1, r, rt << 1 | 1, num);
        }
        pushUp(rt);
    }

    private void pushDown(int rt, int leftN, int rightN) {
        if (update[rt]) {
            update[rt << 1] = true;
            sum[rt << 1] = change[rt] * leftN;
            change[rt << 1] = change[rt];
            lazy[rt << 1] = 0;

            update[rt << 1 | 1] = true;
            sum[rt << 1 | 1] = change[rt] * rightN;
            change[rt << 1 | 1] = change[rt];
            lazy[rt << 1 | 1] = 0;

            update[rt] = false;
        }

        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            sum[rt << 1] += lazy[rt] * leftN;
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1 | 1] += lazy[rt] * rightN;
            lazy[rt] = 0;
        }
    }
}
