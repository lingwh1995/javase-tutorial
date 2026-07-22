package org.bluebridge.thread.section_03_thread_designpattern.threadlocal_storage.threadlocal_storage_b;

/**
 * 执行任务
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryFromDBAction = new QueryFromDBAction();

    private QueryFromHttpAction queryFromHttpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        final ActionContext actionContext = ActionContext.getInstance();
        try {
            Context context = actionContext.getContext();
            queryFromDBAction.execute();
            System.out.println("The name query successful......");
            queryFromHttpAction.execute();
            System.out.println("The idcard query successful......");
            System.out.println("The name is :" + context.getName() + ",idCard is :" + context.getIdCard());
        } finally {
            actionContext.remove();
        }
    }
}
