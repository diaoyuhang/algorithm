package TrainingCamp3.class01;

public class Code04_ColorLeftRight {

    public static void main(String[] args) {
        System.out.println(minPaint("GGGRGRRR"));
    }

    public static int minPaint(String content) {
        int countG = 0;
        char[] cs = content.toCharArray();
        for (char c : cs) {
            if (c == 'G') {
                countG++;
            }
        }

        int limit = 0;
        int needPaint = Integer.MAX_VALUE;
        int length = cs.length;
        int rightG = countG;
        while (limit <= cs.length) {
            int rightR = length - limit - rightG;
            int leftG = countG - rightG;

            needPaint = Math.min(needPaint, rightR + leftG);

            limit++;
            if (limit<=length && cs[limit - 1] == 'G') {
                rightG--;
            }
        }
        return needPaint;
    }

}
