package 训练营1.bfprt_蓄水池;

/**
 * 假设有一个源源吐出不同球的机器，
 * 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
 * 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里
 *
 * @author: Mr.diao
 * @date: 10:52 2021/3/1
 */
public class Code02_ReservoirSampling {
    public static void main(String[] args) {
        System.out.println("hello");
        int all = 100;
        int choose = 10;
        int testTimes = 50000;
        int[] counts = new int[all + 1];
        for (int i = 0; i < testTimes; i++) {
            RandomBox box = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                box.add(num);
            }
            int[] ans = box.choices();
            for (int j = 0; j < ans.length; j++) {
                counts[ans[j]]++;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " times : " + counts[i]);
        }

    }
    public static class RandomBox {
        private int[] bag;
        private int N;
        private int count;

        public RandomBox(int capacity) {
            bag = new int[capacity];
            N = capacity;
            count = 0;
        }

        //随机产生一个[1,max+1)之间的数
        public int rand(int max) {
            return (int) (Math.random() * max) + 1;
        }

        public void add(int num) {
            count++;
            if (count <= N) {
                bag[count - 1] = num;
            } else {
                //如果在[1,num+1)上产生一个随机数，在[1,N)之内，则将num这个数放入袋子中
                if (rand(num) <= N) {
                    //在[1,N+1)内随机选择一个数，替换掉
                    bag[rand(N) - 1] = num;
                }
            }
        }

        public int[] choices() {

            int[] ans = new int[N];
            System.arraycopy(bag, 0, ans, 0, N);
            return ans;
        }
    }

}
