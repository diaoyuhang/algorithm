package TrainingCamp3.class04;

import java.util.HashMap;

public class Code03_PreAndInArrayToPosArray {

    public static int[] preInToPos(int[] pre, int[] in) {

        HashMap<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < in.length; i++) {
            inMap.put(in[i], i);
        }
        int[] pos = new int[pre.length];
        process(pre, 0, pre.length - 1, in, 0, in.length - 1, pos, 0, pos.length - 1, inMap);

        return pos;
    }

    //先序遍历的开头节点就是整棵树的头结点
    //通过先序遍历的开头节点，将中序遍历分成左右两个子树的中序遍历
    //头结点是后序遍历的最后一个节点
    private static void process(int[] pre, int leftPre, int rightPre, int[] in, int leftIn, int rightIn, int[] pos, int posLeft, int posRight, HashMap<Integer, Integer> inMap) {
        if (leftPre > rightPre) {
            return;
        }
        if (leftPre == rightPre) {
            pos[posLeft] = pre[leftPre];
            return;
        }
        pos[posRight] = pre[leftPre];
        Integer mid = inMap.get(pre[leftPre]);
        int leftSize = mid - leftIn;
        process(pre, leftPre + 1, leftPre + leftSize, in, leftIn, mid - 1, pos, posLeft, posLeft + leftSize + 1, inMap);
        process(pre, leftPre + leftSize + 1, rightPre, in, mid + 1, rightIn, pos, posLeft + leftSize + 1, posRight - 1, inMap);
    }

}
