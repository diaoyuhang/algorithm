package 训练营3;

/**
 * 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
 * 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
 * 绳子的边缘点碰到X轴上的点，也算盖住
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 17:56 2021/5/1
 */
public class Code01_CordCoverMaxPoint {

    public static int maxPoints(int[] arr, int length) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            //找到距离arr[i]-length最近的点，但要大于等于arr[i]-length
            int index = getNearestPoint(arr, i, arr[i] - length);
            res = Math.max(res, i - index + 1);
        }
        return res;
    }

    private static int getNearestPoint(int[] arr, int r, int value) {
        int l = 0;
        int index = r;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }
}
