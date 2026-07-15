package org.bluebridge.designpattern_02_guarded_suspension.guarded_suspension_c;

import java.util.concurrent.TimeUnit;

/**
 * 测试保护性暂停模式
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class GuardedObjectTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        TimeUnit.MILLISECONDS.sleep(1000);
        for (Integer id : Mailboxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }
    }
}
