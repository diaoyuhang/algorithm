package 训练营3;

/**
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水
 * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容 器可以装下5格水
 * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 10:38 2021/5/4
 */
public class Code11_TrappingRainWater {

    public static int water(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int size = arr.length;

        int leftMax = arr[0];
        int rightMax = arr[1];
        int l = 1;
        int r = size - 2;
        int res = 0;
        while (l <= r) {
            if (leftMax <= rightMax) {
                res += Math.max(0, leftMax - arr[l]);
                leftMax = Math.max(leftMax, arr[l++]);
            } else {
                res += Math.max(0, rightMax - arr[r]);
                rightMax = Math.max(rightMax, arr[r--]);
            }
        }

        return res;
    }
}
