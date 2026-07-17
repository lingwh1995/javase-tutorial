package org.bluebridge.thread.thread_designpattern.threadlocal_storage.threadlocal_storage_a;

/**
 * 从数据库查询
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class QueryFromDBAction {

    public void execute(Context context) {
        try {
            Thread.sleep(1_000L);
            String name = "Alex from db.....";
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
