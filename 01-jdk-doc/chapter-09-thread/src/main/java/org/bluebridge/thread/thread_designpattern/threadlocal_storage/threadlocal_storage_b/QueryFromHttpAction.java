package org.bluebridge.thread.thread_designpattern.threadlocal_storage.threadlocal_storage_b;

/**
 * @author lingwh
 * @desc 从HTTP查询
 * @date 2026/7/9 00:00
 */
public class QueryFromHttpAction {

    public void execute() {
        final Context context = ActionContext.getInstance().getContext();
        String name = context.getName();
        String idCard = getIdCard(name);
        context.setIdCard(idCard);
    }

    public String getIdCard(String name) {
        try {
            Thread.sleep(1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123456789";
    }
}
