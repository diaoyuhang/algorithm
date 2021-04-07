package 训练营2.打表法;

/**
 * 给定一个正整数N，表示有N份青草统一堆放在仓库里
 * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * 不管是牛还是羊，每一轮能吃的草量必须是：
 * 1，4，16，64…(4的某次方)
 * 谁最先把草吃完，谁获胜
 * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
 * 根据唯一的参数N，返回谁会赢
 *
 * @author: Mr.diao
 * @date: 16:50 2021/4/7
 */
public class Code02_EatGrass {
    public static String eatGrass(int n) {
        if (n < 5) {
            return n == 2 || n == 0 ? "羊" : "牛";
        }

        int base = 1;
        while (base <= n) {
            if (eatGrass(n - base).equals("羊")) {
                return "牛";
            }
//            if (base > n / 4) {
//                break;
//            }
            base *= 4;
        }
        return "羊";
    }

    public static String win2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "羊";
        } else {
            return "牛";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(i + ": " + eatGrass(i));
//            System.out.println(i+": "+win2(i));
        }
    }
}
