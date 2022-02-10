package basicClass.class02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: diaoyuhang
 * @date 2022/2/8
 */
public class Code07_TwoQueueImplementStack {

    public static class MyStack {
        private Queue<Integer> queueOne;
        private Queue<Integer> queueTwo;

        public MyStack() {
            queueOne = new LinkedList<>();
            queueTwo = new LinkedList<>();
        }

        public void add(Integer num) {
            queueOne.offer(num);
        }

        public Integer poll() {
            while (queueOne.size() > 1) {
                queueTwo.offer(queueOne.poll());
            }
            Integer result = queueOne.poll();
            Queue temp = queueOne;
            queueOne = queueTwo;
            queueTwo = temp;

            return result;

        }

    }
}
