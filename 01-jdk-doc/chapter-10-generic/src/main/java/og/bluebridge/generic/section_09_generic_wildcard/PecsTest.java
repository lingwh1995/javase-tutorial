package og.bluebridge.generic.section_09_generic_wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * PECS原则测试
 *
 * @author lingwh
 * @date 2026/7/9 11:04
 */
public class PecsTest {

    public static void main(String[] args) {
        System.out.println("--- 订单处理系统 - PECS实际应用 ---\n");

        // 准备不同类型的订单数据
        List<Order> normalOrders = List.of(
                new Order("ORD-001", 100.0),
                new Order("ORD-002", 200.0));

        List<VipOrder> vipOrders = List.of(
                new VipOrder("VIP-001", 500.0, "张三"),
                new VipOrder("VIP-002", 800.0, "李四"));

        // ---------- 场景1：订单金额统计（Producer - extends） ----------
        System.out.println("--- 场景1：订单金额统计 ---");
        double normalTotal = calculateTotal(normalOrders);
        double vipTotal = calculateTotal(vipOrders);
        System.out.println("普通订单总金额: ¥" + normalTotal);
        System.out.println("VIP订单总金额: ¥" + vipTotal);
        System.out.println("所有订单总金额: ¥" + (normalTotal + vipTotal));

        // ---------= 场景2：订单数据收集（Consumer - super）---------=
        System.out.println("\n--- 场景2：订单数据收集 ---");
        List<Order> allOrders = new ArrayList<>();
        collectOrders(allOrders, normalOrders);
        collectOrders(allOrders, vipOrders);
        System.out.println("收集到的订单数量: " + allOrders.size());

        // ---------- 场景3：订单导出（Producer - extends） ----------
        System.out.println("\n--- 场景3：订单导出 ---");
        exportToCsv(normalOrders, "normal_orders.csv");
        exportToCsv(vipOrders, "vip_orders.csv");

        // ---------- 场景4：批量添加订单（Consumer - super） ----------
        System.out.println("\n--- 场景4：批量添加订单 ---");
        List<Object> orderPool = new ArrayList<>();
        batchAddOrders(orderPool);
        System.out.println("订单池大小: " + orderPool.size());
    }

    /**
     * Producer场景：统计订单总金额 使用 ? extends Order 可以接收 Order 及其子类（VipOrder等）
     */
    public static double calculateTotal(List<? extends Order> orders) {
        double total = 0;
        for (Order order : orders) {
            total += order.getAmount();
        }
        return total;
    }

    /**
     * Consumer场景：收集订单到统一列表 使用 ? super Order 可以写入 Order 及其子类到父类列表
     */
    public static void collectOrders(List<? super Order> target, List<? extends Order> source) {
        for (Order order : source) {
            target.add(order);
        }
    }

    /**
     * Producer场景：导出订单到CSV 使用 ? extends Order 支持导出不同类型的订单
     */
    public static void exportToCsv(List<? extends Order> orders, String filename) {
        System.out.println("导出 " + orders.size() + " 条订单到 " + filename);
        for (Order order : orders) {
            System.out.println("  - " + order.getOrderId() + ": ¥" + order.getAmount());
        }
    }

    /**
     * Consumer场景：批量添加订单 使用 ? super Order 可以向任意父类容器添加订单
     */
    public static void batchAddOrders(List<? super Order> orderList) {
        orderList.add(new Order("BATCH-001", 999.0));
        orderList.add(new VipOrder("BATCH-VIP-001", 1999.0, "VIP客户"));
    }
}

/**
 * 基础订单类
 */
class Order {
    private String orderId;
    private double amount;

    public Order(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }
}

/**
 * VIP订单类（继承Order）
 */
class VipOrder extends Order {
    private String vipName;

    public VipOrder(String orderId, double amount, String vipName) {
        super(orderId, amount);
        this.vipName = vipName;
    }

    public String getVipName() {
        return vipName;
    }
}
