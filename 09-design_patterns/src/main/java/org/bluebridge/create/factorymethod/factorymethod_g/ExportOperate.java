package org.bluebridge.create.factorymethod.factorymethod_g;

/**
 * 实现导出数据的业务功能的对象
 *
 * @author lingwh
 * @date 2019/9/3 17:02
 */
public class ExportOperate {

    public boolean export(int type, String data) {
        ExportFileApi exportFileApi = factoryMethod(type);
        return exportFileApi.export(data);
    }

    public ExportFileApi factoryMethod(int type) {
        ExportFileApi api = null;
        // 根据类型来选择究竟要创建哪一种导出文件对象
        if (type == 1) {
            api = new ExportTxtFile();
        } else if (type == 2) {
            api = new ExportDB();
        }
        return api;
    }
}
