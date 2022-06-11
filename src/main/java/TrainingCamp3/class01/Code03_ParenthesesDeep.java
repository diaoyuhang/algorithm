package TrainingCamp3.class01;

public class Code03_ParenthesesDeep {

    public static void main(String[] args) {
        System.out.println(deep("))))((()))(())((()()()()()()())))"));
    }

    public static int deep(String content) {
        int[] validLength = new int[content.length()];
        validLength[0] = 0;
        int maxLength = 0;
        char[] cs = content.toCharArray();

        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == '(') {
                validLength[i] = 0;
            } else {
                int length = 0;
                if (validLength[i - 1] > 0 && i - validLength[i - 1] - 1 >= 0 && cs[i - validLength[i - 1] - 1] == '(') {
                    length = validLength[i - 1] + 2;
                    if (i - validLength[i - 1] - 2 > 0) {
                        length += validLength[i - validLength[i - 1] - 2];
                    }
                } else {
                    if (cs[i - 1] == '(') {
                        length = 2;
                        if (i - 2 >= 0 && validLength[i - 2] > 0) {
                            length = length + validLength[i - 2];
                        }
                    }
                }
                validLength[i] = length;
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }
}
