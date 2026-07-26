package org.bluebridge.structure.adapter.adapter_l;

/**
 * 适配器模式客户端
 *
 * @author lingwh
 * @date 2026/7/22 11:25
 */
public class Client {

    public static void main(String[] args) {
        // 返回的的数据是 int[] 类型数据
        int[] result = DataCenter.getResult();
        // 将 int[] 类型数据传入到 ArrayAdapter 的构造方法中
        // ArrayAdapter 会重写 ArrayList 的部分方法
        ArrayAdapter arrayAdapter = new ArrayAdapter(result);
        DealCenter.deal(arrayAdapter);
    }
}
