package TrainingCamp3.class06;

public class Code03_JumpGame {
    public static int jumpNum(int[] arr) {
        int cur = 0;
        int next = 0;
        int step = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }
}
