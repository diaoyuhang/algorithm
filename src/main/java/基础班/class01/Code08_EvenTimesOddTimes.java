package 基础班.class01;

public class Code08_EvenTimesOddTimes {

    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int a : arr) {
            eor ^= a;
        }
        //取最右边的1
        int rightOne = eor & (~eor + 1);
        int result1 = 0;
        for (int a : arr) {
            if ((a & rightOne) != 0) {
                result1 ^= a;
            }
        }
        System.out.println(result1 + " " + (eor ^ result1));
    }

    public static void main(String[] args) {
        int[] arr={1,1,1,1,3,3,4,4,4,4,5,5,5,9,9,9};
        printOddTimesNum2(arr);
    }
}
