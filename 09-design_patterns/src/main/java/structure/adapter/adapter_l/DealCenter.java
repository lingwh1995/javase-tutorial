package structure.adapter.adapter_l;

import java.util.List;

/**
 * @author lingwh
 * @desc 数据处理中心
 * @date 2026/7/9 00:00
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
