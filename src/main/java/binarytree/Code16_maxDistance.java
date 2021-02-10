package binarytree;

/**
 * @author: Mr.diao
 * @date: 15:19 2021/2/10
 */
public class Code16_maxDistance {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        Integer maxDistance;
        Integer height;

        public Info(Integer maxDistance, Integer height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static Integer maxDistance2(Node header) {
        if (header == null) {
            return 0;
        }
        return process(header).maxDistance;
    }

    private static Info process(Node header) {
        if (header == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(header.left);
        Info rightInfo = process(header.right);
        //拿到左右子树的最大高度
        Integer height = Math.max(leftInfo.height, rightInfo.height) + 1;

        Integer maxDistance = leftInfo.height + rightInfo.height + 1;
        maxDistance = Math.max(maxDistance, leftInfo.maxDistance);
        maxDistance = Math.max(maxDistance, rightInfo.maxDistance);

        return new Info(maxDistance, height);
    }
}
