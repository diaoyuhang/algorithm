package TrainingCamp3.class07;

public class Code03_MinPatches {

    public static int minPatches(int[] arr, int k) {
        int range = 0;
        int patches = 0;

        for (int a : arr) {
            while (a > range + 1) {
                range += range + 1;
                patches++;
                if (range >= k) {
                    return patches;
                }

                range += a;
                if (range >= k) {
                    return patches;
                }
            }
        }
        while (k > range + 1) {
            range = range + 1;
            patches++;
        }
        return patches;
    }
}
