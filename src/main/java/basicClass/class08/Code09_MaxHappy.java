package basicClass.class08;

import java.util.ArrayList;
import java.util.List;

public class Code09_MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    public static class Info {
        public int yes;
        public int no;

        public Info(int maxHappyOne, int maxHappyTwo) {
            this.yes = maxHappyOne;
            this.no = maxHappyTwo;
        }
    }

    public static int maxHappy(Employee employee) {
        if (employee == null) {
            return 0;
        }
        Info info = process(employee);

        return Math.max(info.yes, info.no);
    }

    private static Info process(Employee employee) {
        if (employee == null) {
            return new Info(0, 0);
        }
        int yes = employee.happy;
        int no = 0;
        for (Employee emp : employee.nexts) {
            Info info = process(emp);
            yes += info.yes;
            no += Math.max(info.no, info.yes);
        }

        return new Info(yes, no);
    }
}
