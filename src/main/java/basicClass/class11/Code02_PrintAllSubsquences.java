package basicClass.class11;

import java.util.HashSet;

public class Code02_PrintAllSubsquences {

    public static void getAllSubSequences(char[] characters, HashSet<String> sub, String path, int index) {
        if (index == characters.length) {
            sub.add(path);
            return;
        }
        String no = path;
        getAllSubSequences(characters, sub, no, index + 1);
        String yes = path + characters[index];
        getAllSubSequences(characters, sub, yes, index + 1);
    }

    public static void printAllSubSequences(String str){
        HashSet<String> set = new HashSet<>();
        getAllSubSequences(str.toCharArray(),set,"",0);
        System.out.println(set);
    }

    public static void main(String[] args) {
        printAllSubSequences("abc");
    }
}
