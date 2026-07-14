package create.factorymethod.factorymethod_g;

/**
 * 扩展的导出操作
 *
 * @author lingwh
 * @date 2019/9/3 17:10
 */
public class ExportOperate2 extends ExportOperate {

    @Override
    public ExportFileApi factoryMethod(int type) {
        ExportFileApi api = null;
        // 根据类型来选择究竟要创建哪一种导出文件对象
        if (type == 1) {
            api = new ExportTxtFile();
        } else if (type == 2) {
            api = new ExportDB();
        } else if (type == 3) {
            api = new ExportXml();
        }
        return api;
    }
}
