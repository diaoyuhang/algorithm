package TrainingCamp3.class06;

public class Code01_MaxEOR {

    public static class Node {
        public Node[] nexts = new Node[2];
    }

    public static class NumTree {
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int more = 31; more >= 0; more--) {
                int path = (num >> more) & 1;
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int eor) {
            Node cur = head;
            int res = 0;
            for (int more = 31; more >= 0; more--) {
                int path = (eor >> more) & 1;
                int better = more == 31 ? path : (path ^ 1);
                better = cur.nexts[better] != null ? better : (better ^ 1);
                res |= (path ^ better) << more;
                cur = cur.nexts[better];
            }
            return res;
        }
    }

    public static int maxXorSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;

        int eor = 0;
        NumTree numTree = new NumTree();
        numTree.add(0);

        for (int i = 0; i < nums.length; i++) {
            eor ^= nums[i];
            max = Math.max(max, numTree.maxXor(eor));
            numTree.add(eor);
        }
        return max;
    }
}
