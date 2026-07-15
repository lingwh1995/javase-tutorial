package action.command.command_b;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建命令调用程序类
 *
 * @author lingwh
 * @date 2019/8/2 9:04
 */
public class Handler {

    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
