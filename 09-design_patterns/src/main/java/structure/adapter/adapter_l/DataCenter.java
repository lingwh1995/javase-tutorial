package structure.adapter.adapter_l;

/**
 * @author lingwh
 * @desc 数据中心
 * @date 2026/7/9 00:00
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
