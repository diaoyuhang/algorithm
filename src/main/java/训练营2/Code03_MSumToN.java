package 训练营2;

/**
 * 定义一种数：可以表示成若干（数量>1）连续正数和的数
 * 比如:
 * 5 = 2+3，5就是这样的数
 * 12 = 3+4+5，12就是这样的数
 * 1不是这样的数，因为要求数量大于1个、连续正数和
 * 2 = 1 + 1，2也不是，因为等号右边不是连续正数
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
 *
 * @author: Mr.diao
 * @date: 17:26 2021/4/7
 */
public class Code03_MSumToN {

    public static boolean isMSum1(int n) {
        for (int i = 1; i < n; i++) {
            int sum = i;
            for (int j = i + 1; j < n; j++) {
                sum += j;
                if (sum > n) {
                    break;
                }
                if (sum == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMsum2(int n) {
        if (n < 3) {
            return false;
        }
        return (n & (n - 1)) != 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + isMsum2(i));
        }
    }
}
