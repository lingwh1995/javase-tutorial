package org.bluebridge.thread.thread_designpattern.threadlocal_storage.threadlocal_storage_b;

/**
 * @author lingwh
 * @desc 动作上下文
 * @date 2026/7/9 00:00
 */
public class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context> (){
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    public static class ContextHolder {
        private static final ActionContext context = new ActionContext();
    }

    public static ActionContext getInstance() {
        return ContextHolder.context;
    }

    public Context getContext() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }
}
