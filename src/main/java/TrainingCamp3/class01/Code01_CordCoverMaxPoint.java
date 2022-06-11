package TrainingCamp3.class01;

public class Code01_CordCoverMaxPoint {

    public int cordCoverMaxPoint(int[] arr, int k) {

        int left = 0;
        int right = 0;
        int maxPoint = 0;
        while (left < arr.length) {
            while (right < arr.length && arr[right] - arr[left] <= k) {
                right++;
            }
            maxPoint = Math.max(maxPoint, right - left);
            left++;
        }

        return maxPoint;
    }
}
