package TrainingCamp3.class02;

public class Code04_TrappingRainWater {

    public static int water(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int leftMax = 0;
        int rightMax = arr.length - 1;
        int l = 1;
        int r = arr.length - 2;

        int index = 1;
        int water = 0;
        while (l <= r) {
            if (leftMax < rightMax) {
                water += Math.max(0, leftMax - arr[l]);
                leftMax = Math.max(leftMax, arr[l++]);
            } else {
                water += Math.max(0, rightMax - arr[r]);
                rightMax = Math.max(rightMax, arr[r--]);
            }
        }
        return water;
    }
}
