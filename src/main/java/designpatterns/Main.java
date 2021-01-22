package designpatterns;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 16:28 2020/10/16
 */
public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.name = "diao";
        msg.setMsg("<html>www.baidu.com</html>");

    }

}

class Msg {
    String name;
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter<T> {
    boolean doFilter(T t);
}

class HtmlFilter implements Filter<Msg> {

    @Override
    public boolean doFilter(Msg msg) {
        if ("".equals(msg.name))
            return false;

        msg.setMsg(msg.getMsg().replace("<", "["));
        msg.setMsg(msg.getMsg().replace(">", "]"));
        return true;
    }
}

class FilterChain implements Filter<Msg> {
    private List<Filter<Msg>> filters = new LinkedList<>();

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public boolean doFilter(Msg msg) {
        for (Filter<Msg> filter : filters) {
            if (!filter.doFilter(msg)) {
                return false;
            }
        }
        return true;
    }
}
