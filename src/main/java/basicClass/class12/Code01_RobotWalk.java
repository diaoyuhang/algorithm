package basicClass.class12;

public class Code01_RobotWalk {

    public static void main(String[] args) {
        System.out.println(walkWay(7, 4, 9, 5));
    }

    /**
     * @param n 总共n个位置
     * @param m 其实位置
     * @param k 走k步
     * @param p 到达p点
     * @return
     */
    public static int walkWay(int n, int m, int k, int p) {
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p > n) {
            return 0;
        }
        return process(n, m, k, p);
    }

    private static int process(int n, int cur, int rest, int p) {
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }

        int res = 0;
        if (cur == 1) {
            res += process(n, cur + 1, rest - 1, p);
            return res;
        }

        if (cur == n) {
            res += process(n, cur - 1, rest - 1, p);
            return res;
        }

        res += process(n, cur + 1, rest - 1, p);
        res += process(n, cur - 1, rest - 1, p);
        return res;
    }

    public static int dpWay(int n, int m, int k, int p){
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p > n) {
            return 0;
        }

        new int[][];

        return 0;
    }
}
