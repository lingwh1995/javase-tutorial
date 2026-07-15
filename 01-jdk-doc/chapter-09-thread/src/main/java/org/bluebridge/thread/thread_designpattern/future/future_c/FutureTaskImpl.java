package org.bluebridge.thread.thread_designpattern.future.future_c;

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
