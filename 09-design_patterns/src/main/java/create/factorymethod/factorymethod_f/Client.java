package create.factorymethod.factorymethod_f;

/**
 * @author lingwh
 * @date
 * @date 2019/9/3 16:50
 */
public class Client {
    public static void main(String[] args) {
        // 创建需要使用的Creator对象
        ExportOperate operate = new ExportOperate();
        // 调用输出数据的功能方法，传入选择到处类型的参数
        operate.export(1, "测试数据");
        operate.export(2, "测试数据");
    }
}
