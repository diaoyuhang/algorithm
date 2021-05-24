package 训练营3;

/**
 * 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
 * 比如给定:
 * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
 * int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
 * {4,5,2,6,7,3,1}
 *
 * @author: Mr.diao
 * @date: 16:47 2021/5/24
 */
public class Code24_PreAndInArrayToPosArray {

    public static int[] preInToPos(int[] pre, int[] in) {
        int length = pre.length;
        int[] pos = new int[pre.length];
        process(pre, 0, length - 1, in, 0, length - 1, pos, 0, length - 1);
        return pos;
    }

    private static void process(int[] pre, int l1, int r1, int[] in, int l2, int r2, int[] pos, int l3, int r3) {
        if (l1 > r1) {
            return;
        }
        if (l1 == r1) {
            pos[r3] = pre[l1];
            return;
        }
        pos[r3] = pre[l1];
        int mid = l2;

        //这一步可以使用hashmap来代替，查找中间节点
        for (; mid < r2; mid++) {
            if (in[mid] == pre[l1]) {
                break;
            }
        }
        int size = mid - l2;
        process(pre, l1 + 1, l1 + size, in, l2, mid - 1, pos, l3, l3 + size - 1);
        process(pre, l1 + size + 1, r1, in, mid + 1, r2, pos, r3 - size, r3 - 1);
    }
}
