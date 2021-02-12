package binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 15:14 2021/2/12
 */
public class Code17_MaxHappy {
    public static void main(String[] args) {

    }

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    public static class Info {
        public int yes;//包含当前结点的值
        public int no;//不包含当前结点的值

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static Integer maxHappy2(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Info info = process(boss);
        return Math.max(info.yes, info.no);
    }

    private static Info process(Employee boss) {
        if (boss.nexts.isEmpty()) {
            return new Info(boss.happy, 0);
        }

        int yes = boss.happy;
        int no = 0;

        List<Employee> nexts = boss.nexts;
        for (Employee e : nexts) {
            Info info = process(e);
            yes += info.no;
            no += Math.max(info.yes, info.no);
        }
        return new Info(yes,no);
    }
}
