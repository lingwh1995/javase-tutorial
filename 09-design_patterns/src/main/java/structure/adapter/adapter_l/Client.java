package structure.adapter.adapter_l;

/**
 * @author lingwh
 * @desc 适配器模式客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        // 返回的的数据是int[]类型数据
        int[] result = DataCenter.getResult();
        // 将int[]类型数据传入到ArrayAdapter的构造方法中
        // ArrayAdapter会重写ArrayList的部分方法
        ArrayAdapter arrayAdapter = new ArrayAdapter(result);
        DealCenter.deal(arrayAdapter);
    }
}
