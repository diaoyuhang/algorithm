package 训练营1.线段树;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 17:35 2021/3/28
 */
public class Code01_SegmentTree {
    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }

    public static class SegmentTree {
        public int maxN;
        public int[] sum;
        public int[] arr;
        public int[] lazy;
        public boolean[] update;
        public int[] change;

        public SegmentTree(int[] origin) {

            this.maxN = origin.length + 1;
            arr = new int[this.maxN];
            for (int i = 1; i < this.maxN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[this.maxN << 2];
            lazy = new int[this.maxN << 2];
            update = new boolean[this.maxN << 2];
            change = new int[this.maxN << 2];
        }

        //rt代表在某一段范围在二叉树中的位置
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

        //L-R 目标范围
        //l-r 数组范围
        //num 目标范围需要增加的值
        public void add(int L, int R, int num, int l, int r, int rt) {

            if (L <= l && r <= R) {
                sum[rt] += num * (r - l + 1);
                lazy[rt] += num;
                return;
            }

            int mid = (l + r) >> 1;
            //将当前的已有的任务下发
            pushDown(rt, mid - l + 1, r - mid);

            if (L <= mid) {
                add(L, R, num, l, mid, rt << 1);
            }

            if (R > mid) {
                add(L, R, num, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        //leftNum 左孩子的元素个数
        //rightNum 有孩子的元素个数
        private void pushDown(int rt, int leftNum, int rightNum) {
            if (update[rt]) {
                update[rt << 1] = true;
                change[rt << 1] = change[rt];
                lazy[rt << 1] = 0;
                sum[rt << 1] = change[rt] * leftNum;

                update[rt << 1 | 1] = true;
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1 | 1] = change[rt] * rightNum;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * leftNum;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rightNum;
                lazy[rt] = 0;
            }
        }

        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

        public void update(int L, int R, int num, int l, int r, int rt) {
            if (L <= l && R >= r) {
                update[rt] = true;
                change[rt] = num;
                sum[rt] = num * (r - l + 1);
                lazy[rt]=0;
                return;
            }

            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);

            if (L <= mid) {
                update(L, R, num, l, mid, rt << 1);
            }
            if (mid< R) {
                update(L, R, num, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }
    }

    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }
}
