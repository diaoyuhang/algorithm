package 训练营3;

/**
 * 给定一个有序数组arr，给定一个正数aim
 * <p>
 * 1）返回累加和为aim的，所有不同二元组
 * <p>
 * 2）返回累加和为aim的，所有不同三元组
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 14:30 2021/5/4
 */
public class Code13_PrintUniquePairAndTriad {

    public static void firstQuestion(int[] arr, int aim) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            if (arr[l] + arr[r] == aim) {
                if (l != 0 && arr[l - 1] != arr[l]) {
                    System.out.println(arr[l] + "," + arr[r]);
                }
                l++;
                r--;
            } else if (arr[l] + arr[r] > aim) {
                r--;
            } else {
                l++;
            }
        }
    }

    public static void secondQuestion(int[] arr, int aim) {
        if (arr == null || arr.length < 3) {
            return;
        }

        for (int i = 0; i < arr.length - 2; i++) {
            if (i != 0 && arr[i] != arr[i - 1]) {
                process(arr, i, i + 1, arr.length - 1, aim - arr[i]);
            }
        }

    }

    private static void process(int[] arr, int first, int second, int third, int aim) {
        while (second < third) {
            if (arr[second] + arr[third] > aim) {
                third--;
            } else if (arr[second] + arr[third] < aim) {
                second++;
            } else {
                if (second != first + 1 && arr[second] != arr[second - 1]) {
                    System.out.println(arr[first] + "," + arr[second] + "," + arr[third]);
                }
                second++;
                third--;
            }
        }

    }
}
