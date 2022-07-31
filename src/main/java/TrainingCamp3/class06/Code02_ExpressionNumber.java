package TrainingCamp3.class06;

public class Code02_ExpressionNumber {

    public static int num1(String express, Boolean desired) {
        char[] exp = express.toCharArray();
        return f1(exp, desired, 0, exp.length - 1);
    }

    private static int f1(char[] exp, Boolean desired, int left, int right) {
        if (left == right) {
            if (exp[left] == '1') {
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }
        int res = 0;
        if (desired) {
            for (int L = left + 1; L < right; L += 2) {
                switch (exp[L]) {
                    case '&':
                        res += f1(exp, true, left, L - 1) * f1(exp, true, L + 1, right);
                        break;
                    case '|':
                        res += f1(exp, true, left, L - 1) * f1(exp, false, L + 1, right);
                        res += f1(exp, false, left, L - 1) * f1(exp, true, L + 1, right);
                        res += f1(exp, true, left, L - 1) * f1(exp, true, L + 1, right);
                        break;
                    case '^':
                        res += f1(exp, true, left, L - 1) * f1(exp, false, L + 1, right);
                        res += f1(exp, false, left, L - 1) * f1(exp, true, L + 1, right);
                        break;
                }
            }
            return res;
        } else {
            for (int L = left + 1; L < right; L += 2) {
                switch (exp[L]) {
                    case '&':
                        res += f1(exp, false, left, L - 1) * f1(exp, true, L + 1, right);
                        res += f1(exp, true, left, L - 1) * f1(exp, false, L + 1, right);
                        break;
                    case '|':
                        res += f1(exp, false, left, L - 1) * f1(exp, false, L + 1, right);
                        break;
                    case '^':
                        res += f1(exp, true, left, L - 1) * f1(exp, true, L + 1, right);
                        res += f1(exp, false, left, L - 1) * f1(exp, false, L + 1, right);
                        break;
                }
            }
            return res;
        }
    }

    public static int dp(String express, Boolean desired) {
        char[] exp = express.toCharArray();
        int length = exp.length;
        int[][] tMap = new int[length][length];
        int[][] fMap = new int[length][length];
        for (int i = 0; i < length; i += 2) {
            tMap[i][i] = exp[i] == '1' ? '1' : '0';
            fMap[i][i] = exp[i] == '0' ? '0' : '1';
        }

        for (int row = length - 3; row >= 0; row += 2) {
            for (int col = row + 2; col < length; col += 2) {
                for (int i = row + 1; i < col; i += 2) {

                    switch (exp[i]) {
                        case '&':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '|':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];//f1(exp, true, left, L - 1) * f1(exp, false, L + 1, right);
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];//f1(exp, false, left, L - 1) * f1(exp, true, L + 1, right);
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];//f1(exp, true, left, L - 1) * f1(exp, true, L + 1, right);
                            break;
                        case '^':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                    }
                    switch (exp[i]) {
                        case '&':
                            fMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            fMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '|':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '^':
                            fMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                    }

                }
            }
        }
        return desired ? tMap[0][length - 1] : fMap[0][length - 1];
    }
}
