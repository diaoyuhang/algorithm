package TrainingCamp3.class02;

public class Code06_PrintUniquePairAndTriad {

    public static void printUniquePair(int[] arr, int k) {

        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            if (arr[l] + arr[r] == k) {
                System.out.println(arr[l] + "  " + arr[r]);
                l++;
                r--;
            } else if (arr[l] + arr[r] > k) {
                r--;
            } else {
                l++;
            }
        }
    }

    public static void printUniqueTriad(int[] arr, int k) {

        for (int i = 0; i < arr.length; i++) {
            printRest(arr, i, i + 1, arr.length - 1, k - arr[i]);
        }
    }

    private static void printRest(int[] arr, int i, int l, int r, int rest) {
        while (l < r) {

            if (arr[l] + arr[r] == rest) {
                System.out.println(arr[i] + " " + arr[l] + "  " + arr[r]);
                l++;
                r--;
            } else if (arr[l] + arr[r] > rest) {
                r--;
            } else {
                l++;
            }

        }
    }
}
