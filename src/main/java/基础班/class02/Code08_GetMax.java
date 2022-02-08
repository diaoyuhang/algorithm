package 基础班.class02;

/**
 * @author: diaoyuhang
 * @date 2022/2/8
 */
public class Code08_GetMax {

    public Integer getMax(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return process(arr, 0, arr.length - 1);
    }

    public Integer process(Integer[] arr, Integer left, Integer right) {
        if (left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >> 1);

        return Math.max(process(arr, left, mid), process(arr, mid + 1, right));
    }
}
