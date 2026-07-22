package org.bluebridge.thread.section_03_thread_designpattern.future.future_d;

/**
 * Future 模式服务
 *
 * @author lingwh
 * @date 2019/10/10 16:55
 */
public class FutureService {

    public Data request(final String request) {
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(request);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }
}
