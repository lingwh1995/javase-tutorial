package org.bluebridge.thread.thread_designpattern.threadlocal_storage.threadlocal_storage_b;

/**
 * 动作上下文
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
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
