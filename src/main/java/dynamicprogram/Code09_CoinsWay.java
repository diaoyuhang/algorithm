package dynamicprogram;

/**
 * @author: Mr.diao
 * @date: 17:37 2021/2/24
 */
public class Code09_CoinsWay {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        int sum = 350;
        System.out.println(ways(arr, sum));
//        System.out.println(waysdp(arr, sum));
    }

    public static int way1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, aim, 0,0);
    }

    private static int process(int[] arr, int aim,int index, int sum) {
        if (sum == aim) {
            return 1;
        }
        if (sum > aim) {
            return 0;
        }
        int ans = 0;
        for (int i = index; i < arr.length; i++) {
            int result = process(arr, aim, sum + arr[i]);
            ans += result;
        }

        return ans;
    }

    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    // 如果自由使用arr[index...]的面值，组成rest这么多钱，返回方法数 （1 , 6）
    public static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) { // 无面值的时候
            return rest == 0 ? 1 : 0;
        }
        // 有面值的时候
        int ways = 0;
        // arr[index] 当钱面值
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - zhang * arr[index]);
        }
        return ways;
    }
}
