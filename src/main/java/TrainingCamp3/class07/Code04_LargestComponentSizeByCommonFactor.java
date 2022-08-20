package TrainingCamp3.class07;

import java.util.HashMap;
import java.util.Stack;

public class Code04_LargestComponentSizeByCommonFactor {


    public static int largestComponentSize(int[] arr) {
        UnionFindSet unionFindSet = new UnionFindSet(arr.length);
        HashMap<Integer, Integer> fatherMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int limit = (int) Math.sqrt(num);

            for (int j = 1; j <= limit; j++) {
                if (num % j == 0) {
                    if (j != 1) {
                        if (!fatherMap.containsKey(j)) {
                            fatherMap.put(j, i);
                        } else {
                            unionFindSet.union(fatherMap.get(j), i);
                        }
                    }

                    int other = num / j;
                    if (other != 1) {
                        if (!fatherMap.containsKey(other)) {
                            fatherMap.put(other, i);
                        } else {
                            unionFindSet.union(fatherMap.get(other), i);
                        }
                    }
                }
            }
        }
        return unionFindSet.maxSize();
    }

    public static class UnionFindSet {
        public int[] parents;// parents[i]=j 是 arr[j] 是arr[i]的父亲
        public int[] sizes;

        public UnionFindSet(int length) {
            parents = new int[length];
            sizes = new int[length];

            for (int i = 0; i < length; i++) {
                parents[i] = i;
                sizes[i] = i;
            }
        }

        public int maxSize() {
            int num = 0;
            for (int size : sizes) {
                num = Math.max(size, num);
            }
            return num;
        }

        //传入的
        private int findHead(int element) {
            Stack<Integer> stack = new Stack<>();

            while (element != parents[element]) {
                element = parents[element];
                stack.push(element);
            }

            while (!stack.isEmpty()) {
                Integer num = stack.pop();
                parents[num] = element;
            }
            return element;
        }

        public void union(int a, int b) {
            int aI = findHead(a);
            int bI = findHead(b);
            if (aI != bI) {
                int big = sizes[aI] > sizes[bI] ? aI : bI;
                int small = big == aI ? bI : aI;
                parents[small] = big;
                sizes[big] = sizes[aI] + sizes[bI];
                sizes[small] = 0;
            }
        }
    }
}
