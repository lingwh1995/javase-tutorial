package org.bluebridge.thread.section_03_thread_designpattern.future.future_b;

/**
 * FutureTask 实现类
 *
 * @author lingwh
 * @date 2019/10/10 16:15
 */
public class FutureTaskImpl implements FutureTask<String> {

    @Override
    public String call() {
        return "FutureTaskImpl.call()的返回值:I am FutureTaskImpl......";
    }
}
