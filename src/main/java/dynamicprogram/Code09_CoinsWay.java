package dynamicprogram;

/**
 * @author: Mr.diao
 * @date: 17:37 2021/2/24
 */
public class Code09_CoinsWay {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        int sum = 350;
        System.out.println(way1(arr, sum));
        System.out.println(dpWay(arr, sum));
    }

    public static int way1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, aim, 0);
    }

    private static int process(int[] arr, int rest, int index) {

        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        if (rest == 0) {
            return 1;
        }

        int ways = 0;
        for (int zhangShu = 0; zhangShu * arr[index] <= rest; zhangShu++) {
            ways += process(arr, rest - zhangShu * arr[index], index + 1);
        }

        return ways;
    }

    public static int dpWay(int[] arr, int aim) {

        int[][] dp = new int[arr.length + 1][aim + 1];

//        dp[arr.length][0] = 1;
        for (int i=0;i<=arr.length;i++){
            dp[i][0]=1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 1; j <= aim; j++) {
                int ways = 0;
                for (int zhangShu = 0; zhangShu * arr[i] <= j; zhangShu++) {
                    ways += dp[i + 1][j - zhangShu * arr[i]];
                }
                dp[i][j] = ways;
            }
        }
        return dp[0][aim];
    }
}
