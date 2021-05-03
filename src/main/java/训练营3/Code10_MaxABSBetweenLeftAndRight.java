package 训练营3;

/**
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的 作为右部分。
 * <p>
 * 但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的， 左部分最大值减去右部分最大值的绝对值。
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 9:41 2021/5/3
 */
public class Code10_MaxABSBetweenLeftAndRight {

    public static int maxAbs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return Math.abs(max - Math.min(arr[0], arr[1]));
    }

}
