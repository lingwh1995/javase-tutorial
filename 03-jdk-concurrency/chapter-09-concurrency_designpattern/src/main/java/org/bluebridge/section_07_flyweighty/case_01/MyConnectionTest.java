package org.bluebridge.section_07_flyweighty.case_01;

import java.sql.Connection;

/**
 * 测试连接池
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MyConnectionTest {

    public static void main(String[] args) {
        // 初始化连接池
        ConnectionPool connectionPool = new ConnectionPool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Connection conn = connectionPool.borrow();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                connectionPool.free(conn);
            }).start();
        }
    }

    /*
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Connection conn = pool.borrow();
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pool.free(conn);
            }).start();
        }
    }*/
}
