package 训练营1.线段树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * 想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
 * 下面是这个游戏的简化版：
 * 1）只会下落正方形积木
 * 2）[a,b] -> 代表一个边长为b的正方形积木，积木左边缘沿着X = a这条线从上方掉落
 * 3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
 * 4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。
 * <p>
 * 给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，
 * 返回每一次掉落之后的最大高度
 *
 * @author: Mr.diao
 * @date: 10:47 2021/4/7
 */
public class Code02_FallingSquares {

    public static class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int n) {
            int size = n + 1;
            max = new int[n << 2];
            change = new int[n << 2];
            update = new boolean[n << 2];

        }

        public void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        //ln左树的元素个数，rn右树的元素个数
        public void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];

                update[rt << 1] = true;
                update[rt << 1 | 1] = true;

                max[rt << 1] = max[rt];
                max[rt << 1 | 1] = max[rt];

                update[rt] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                update[rt] = true;
                change[rt] = C;
                max[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;

            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.max(left, right);
        }
    }

    public HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] arr : positions) {
            set.add(arr[0]);
            set.add(arr[1] + arr[0] - 1);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer num : set) {
            map.put(num, ++count);
        }
        return map;
    }

    //[1][4] 1代表起点 4代表长度
    public List<Integer> fallingSquares(int[][] positions) {
        //数据离散化
        HashMap<Integer, Integer> map = index(positions);
        SegmentTree segmentTree = new SegmentTree(map.size());
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for (int[] arr : positions) {
            Integer L = map.get(arr[0]);
            Integer R = map.get(arr[1] + arr[0] - 1);
            int height = segmentTree.query(L, R, 1, map.size(), 1) + arr[1];
            segmentTree.update(L, R, height, 1, map.size(), 1);
            max = Math.max(height, max);
            res.add(max);
        }
        return res;
    }
}
