package structure.proxy.virtualproxy.vritualproxy_a;

import java.util.LinkedList;
import java.util.List;

/**
 * 老板
 *
 * @author lingwh
 * @date 2019/9/24 18:12
 */
public class Boss implements Approvable {

    List<String> orders;

    {
        System.out.println("\nBoss出现...\n");
    }

    public Boss() {
        this.orders = new LinkedList<>();
    }

    public Boss(List<String> orders) {
        if (orders != null) {
            this.orders = orders;
        } else {
            this.orders = new LinkedList<>();
        }
    }

    public void addOrder(String order) {
        this.orders.add(order);
    }

    @Override
    public void approve() {
        while (orders.size() > 0) {
            String order = orders.remove(0);
            System.out.println("Boss处理了任务<" + order + ">");
        }
        System.out.println();
    }
}
