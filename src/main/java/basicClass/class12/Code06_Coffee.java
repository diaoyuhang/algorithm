package basicClass.class12;

import java.util.Arrays;

public class Code06_Coffee {

    public static int coffee(int[] arr, int a, int b) {
//        Arrays.sort(arr);
        return process(arr, a, b, 0, 0);
    }

    private static int process(int[] arr, int a, int b, int index, int washLine) {
        if (index == arr.length - 1) {
            return Math.min(Math.max(washLine, arr[index]) + a, arr[index] + b);
        }

        int wash = Math.max(arr[index], washLine) + a;
        int res1 = process(arr, a, b, index + 1, wash);
        res1 = Math.max(wash, res1);

        int dry = arr[index] + b;
        int res2 = process(arr, a, b, index + 1, washLine);
        res2 = Math.max(dry, res2);
        return Math.min(res1, res2);
    }


    public static int coffee2(int[] arr, int a, int b) {
//        Arrays.sort(arr);
        return process(arr, a, b, 0, 0);
    }

    private static int process2(int[] arr, int a, int b, int index, int washLine) {
        if (index == arr.length - 1) {
            return Math.min(Math.max(washLine, arr[index]) + a, arr[index] + b);
        }

        int wash = Math.max(arr[index], washLine) + a;
        int res1 = process2(arr, a, b, index + 1, wash);
//        res1 = Math.max(wash, res1);

        int dry = arr[index] + b;
        int res2 = process2(arr, a, b, index + 1, washLine);
//        res2 = Math.max(dry, res2);
        return Math.min(res1, res2);
    }

    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 5;
        int max = 9;
        for (int i = 0; i < 5000; i++) {
            int[] arr = randomArray(len, max);
            int a = (int) (Math.random() * 5) + 1;
            int b = (int) (Math.random() * 10) + 1;
            if (coffee(arr, a, b) != coffee2(arr, a, b)){
                System.out.println(Arrays.toString(arr));
                System.out.println("a:"+a);
                System.out.println("b:"+b);
            }
        }

    }
}
