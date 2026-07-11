package org.bluebridge.thread.thread_designpattern.future.future_d;

/**
 * @author lingwh
 * @desc Future 数据实现
 * @date 2019/10/10 16:54
 */
public class FutureData implements Data {

    private RealData realData;
    private boolean isReady = false;

    @Override
    public synchronized String getRequest() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.realData.getRequest();
    }

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notify();
    }
}
