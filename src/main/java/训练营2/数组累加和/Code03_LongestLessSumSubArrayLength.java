package 训练营2.数组累加和;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
 * 返回其长度
 *
 * @author: Mr.diao
 * @date: 15:08 2021/4/9
 */
public class Code03_LongestLessSumSubArrayLength {
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //记录arr中每个数字的开始，向右加的最小和
        int[] minSums = new int[arr.length];
        //对应minSums中，每个求出的结果的长度
        int[] minSumIndex = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumIndex[arr.length - 1] = arr.length - 1;

        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] <= 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumIndex[i] = minSumIndex[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumIndex[i] = i;
            }
        }

        int len = 0;
        int sum = 0;
        int max = 0;
        //计算从每个i开始求和<=k的最大长度
        for (int i = 0; i < arr.length; i++) {
            //求出[i,len)的和，满足<=k
            while (len < arr.length && sum + minSums[len] <= k) {
                sum += minSums[len];
                len = minSumIndex[len] + 1;
            }

            max = Math.max(max, len - i);
            if (len > i) {
                //如果len大于当前i的位置，减去当前i的值，进行i+1的位置计算[i+1,len)
                sum -= arr[i];
            } else {
                //i开头的求和不满足<=k
                len = i + 1;
            }

        }
        return max;
    }

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
