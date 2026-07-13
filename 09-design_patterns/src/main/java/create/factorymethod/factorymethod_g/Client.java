package create.factorymethod.factorymethod_g;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2019/9/3 16:50
 */
public class Client {

    public static void main(String[] args) {
        // 这里创建的对象不是原来的ExportOperate对象了，而是扩展了ExportOperate的ExportOperate2这个对象
        ExportOperate2 operate2 = new ExportOperate2();
        // 调用输出数据的功能方法，传入选择到处类型的参数
        operate2.export(1, "测试数据");
        operate2.export(2, "测试数据");
        operate2.export(3, "测试数据");
    }
}
