package org.bluebridge.thread.section_03_thread_designpattern.future.future_d;

/**
 * 真实数据实现
 *
 * @author lingwh
 * @date 2019/10/10 16:53
 */
public class RealData implements Data {

    private String result;

    public RealData(String request) {
        System.out.println("根据" + request + "进行查询..,要花很久时间");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕,获取结果");
        result = "查询结果";
    }

    @Override
    public String getRequest() {
        return result;
    }
}
