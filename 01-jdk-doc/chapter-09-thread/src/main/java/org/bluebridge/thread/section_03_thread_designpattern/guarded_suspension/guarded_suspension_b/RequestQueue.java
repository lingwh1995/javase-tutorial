package org.bluebridge.thread.section_03_thread_designpattern.guarded_suspension.guarded_suspension_b;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 请求队列
 *
 * @author lingwh
 * @date 2019/10/16 10:32
 */
public class RequestQueue {

    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public Request getRequest() {
        Request request = null;
        try {
            request = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return request;
    }

    public void putRequest(Request request) {
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
