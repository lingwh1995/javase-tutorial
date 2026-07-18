package org.bluebridge.lock_07_synchronized_demo_ticket.ticket_06_ticket_window;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * 多线程售票窗口测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TicketWindowTest {

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(1000);
        List<Thread> list = new ArrayList<>();
        // 用来存储买出去多少张票
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {
                // 分析这里的竞态条件
                int count = ticketWindow.sell(randomAmount());
                sellCount.add(count);
            });
            list.add(t);
            t.start();
        }
        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("卖出的票数:" + sellCount.stream().mapToInt(c -> c).sum());
        System.out.println("余票:" + ticketWindow.getCount());
    }

    // Random 为线程安全
    static Random random = new Random();

    // 随机 1~5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}
