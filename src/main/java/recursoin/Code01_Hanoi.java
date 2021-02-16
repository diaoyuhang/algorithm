package recursoin;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 10:44 2021/2/16
 */
public class Code01_Hanoi {
    public static void main(String[] args) {
        fun(4,"left","right","mid");
    }

    public static void fun(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 from:" + from + " to:" + to);
            return ;
        }
        fun(n - 1, from, other, to);
        System.out.println("Move " + n + " from:" + from + " to:" + to);
        fun(n - 1, other, to, from);
    }
}
