package TrainingCamp3.class08;

public class Code03_OneNumber {

    public static int solution(int num) {
        int res = 0;

        int len = getLenOfNum(num);
        if (len == 1) {
            return 1;
        }
        int firstNum = (int) (num / Math.pow(10, len - 1));
        res += firstNum == 1 ? firstNumIsOne(num, len) : firstNumIsNotOne(num, len);
        return res;
    }

    private static int firstNumIsNotOne(int num, int len) {
        int oneZero = (int) Math.pow(10, len - 1);
        int oneNum = (int) Math.pow(10, len - 1);
        oneNum += (((int) Math.pow(10, len - 2))) * (len - 1) * (num / oneZero);
        oneNum += solution(num - (oneZero*(num / oneZero)));
        return oneNum;
    }

    private static int firstNumIsOne(int num, int len) {
        int oneNum = 0;
        //1000000...若干0
        int oneZero = (int) (1 * Math.pow(10, len - 1));
        oneNum += num - oneZero + 1;
        oneNum += (((int) Math.pow(10, len - 2))) * (len - 1);
        oneNum += solution(num - oneZero);
        return oneNum;
    }

    private static int getLenOfNum(int num) {

        int len = 0;
        while (num > 0) {
            num = num / 10;
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(solution(123456));
        System.out.println(solution1(123456));
    }
    public static int solution1(int num) {
        if (num < 1) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i != num + 1; i++) {
            count += get1Nums(i);
        }
        return count;
    }

    public static int get1Nums(int num) {
        int res = 0;
        while (num != 0) {
            if (num % 10 == 1) {
                res++;
            }
            num /= 10;
        }
        return res;
    }
}
