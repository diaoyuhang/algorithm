package 训练营1.斐波拉切问题;

/**
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 * 如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
 * 返回有多少达标的字符串
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 20:58 2021/2/28
 */
public class ZeroLeftOneStringNumber {

    /* *
     * 第一位一定是1，实际是求i-1的
     *  f(5)=f(5-2)+f(5-1)
     * 1    01?
     * 当第二位为0时，第三位只能填1，直接跳到第四位,求后面达标的可能性
     * 1    1?
     * 当第二位为1时，第二位可填0，1
     */
    public static int function() {
        return 0;
    }
}
