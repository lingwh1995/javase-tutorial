package org.bluebridge.thread.thread_designpattern.threadlocal_storage.threadlocal_storage_a;

/**
 * @author lingwh
 * @desc 从数据库查询
 * @date 2026/7/9 00:00
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
