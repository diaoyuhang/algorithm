package TrainingCamp3.class07;

public class Code05_RemoveDuplicateLettersLessLexi {


    public static String remove(String str) {
        char[] chars = str.toCharArray();

        int[] map = new int[256];
        for (char c : chars) {
            map[c]++;
        }

        int minIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            if (--map[chars[i]] == 0) {
                break;
            } else {
                minIndex = chars[minIndex] > chars[i] ? i : minIndex;
            }
        }
        return String.valueOf(chars[minIndex] + remove(str.substring(minIndex + 1).replaceAll(chars[minIndex] + "", "")));
    }
}
