package 训练营2;

/**
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 * 1）能装下6个苹果的袋子
 * 2）能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 * 给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
 *
 * @author: Mr.diao
 * @date: 15:27 2021/4/7
 */
public class Code01_AppleMinBags {

    public static Integer minBag(int apples) {
        if (apples <= 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apples / 8;
        int rest = apples - 8 * bag8;

        while (bag8 >= 0) {
            int b6 = minBag6Base(rest);
            if (b6 != -1) {
                bag6 = b6;
                break;
            }
            bag8--;
            rest += 8;
        }
        return bag6 != -1 ? bag6 + bag8 : -1;
    }

    private static int minBag6Base(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) {
            return -1;
        }
        if (apple < 9) {
            return apple == 6 || apple == 8 ? 1 : -1;
        }
        if (apple < 18) {
            return apple == 12 || apple == 14 || apple == 16 ? 2 : -1;
        }

        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + minBag(i));
        }
    }
}
