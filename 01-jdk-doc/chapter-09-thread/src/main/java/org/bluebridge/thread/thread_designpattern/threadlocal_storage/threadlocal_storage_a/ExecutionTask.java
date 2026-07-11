package org.bluebridge.thread.thread_designpattern.threadlocal_storage.threadlocal_storage_a;

/**
 * @author lingwh
 * @desc 执行任务
 * @date 2026/7/9 00:00
 */
public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryFromDBAction = new QueryFromDBAction();

    private QueryFromHttpAction queryFromHttpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        final Context context = new Context();
        queryFromDBAction.execute(context);
        System.out.println("The name query successful......");
        queryFromHttpAction.execute(context);
        System.out.println("The idcard query successful......");
        System.out.println("The name is :" + context.getName() + ",idCard is :" + context.getIdCard());
    }
}
