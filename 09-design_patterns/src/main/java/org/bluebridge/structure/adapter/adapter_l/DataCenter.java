package org.bluebridge.structure.adapter.adapter_l;

/**
 * 数据中心
 *
 * @author lingwh
 * @date 2026/7/22 10:48
 */
public class DataCenter {

    /**
     * 返回一个数组
     *
     * @return
     */
    public static int[] getResult() {
        int[] result = new int[10];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }
}
