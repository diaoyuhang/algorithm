package TrainingCamp3.class01;

public class Code02_NeedParentheses {

    public boolean isValid(String content) {
        int value = 0;

        char[] cs = content.toCharArray();

        for (char c : cs) {
            if (c == '(') {
                value++;
            } else {
                value--;
                if (value < 0) {
                    return false;
                }
            }
        }
        return value == 0 ? true : false;
    }

    public int needParenthesesNum(String content) {
        int left = 0;
        int value = 0;
        char[] cs = content.toCharArray();
        for (char c : cs) {
            if (c == '(') {
                value++;
            } else {
                if (value == 0) {
                    left++;
                } else {
                    value--;
                }
            }
        }
        return left + value;
    }
}
