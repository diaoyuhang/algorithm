package 训练营3;

/**
 * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
 * 现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将 会被覆盖。
 * 目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。 返回最少需要涂染几个正方形。
 * 如样例所示: s = RGRGR 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 10:08 2021/5/2
 */
public class Code04_ColorLeftRight {
    //定义一个从左到右的虚拟分界线，需要将右边的R变成G，左边的G变成R
    public static int minPaint(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        int count = 0;
        char[] chars = str.toCharArray();
        //统计R的个数,间接得到G的个数
        for (char c : chars) {
            if (c == 'R') {
                count++;
            }
        }
        //定义一个虚拟分界线从-1位置开始
        res = Math.min(res, count);
        int left = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'G') {
                left++;
                res = Math.min(res, left + count);
            } else {
                count--;
                res = Math.min(res, left + count);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "GGGGGG";
        System.out.println(minPaint(test));

    }
}
