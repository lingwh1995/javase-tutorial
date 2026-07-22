package org.bluebridge.thread.section_03_thread_designpattern.threadlocal_storage.threadlocal_storage_b;

/**
 * 从HTTP查询
 *
 * @author lingwh
 * @date 2026/4/23 16:29
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
