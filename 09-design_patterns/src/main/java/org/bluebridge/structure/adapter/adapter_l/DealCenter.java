package org.bluebridge.structure.adapter.adapter_l;

import java.util.List;

/**
 * 数据处理中心
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DealCenter {

    /**
     * 打印list
     *
     * @param list
     */
    public static void deal(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
