package sort;

/**
 * @Author: Mr.diao
 * @Description: 折半查找
 * @Date: 16:01 2020/10/16
 */
public class Code04_BSExist {

    public static boolean exist(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        while (l < r) {
                mid = l + (r - l) >> 1;
                if (arr[mid] == num) {
                    return true;
                } else if (num < arr[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            return false;

    }
    public static void main(String[] args){
        int[] arr={1,2,3,4,5,6,7,8,9,13,45,778};
        System.out.println(exist(arr,0));
    }
}
