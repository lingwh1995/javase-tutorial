package org.bluebridge.create.factorymethod.factorymethod_e;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/8/2 10:32
 */
public class Client {

    public static void main(String[] args) {
        // 创建提供导出文件到文本文档的 API 的对象
        AbstractExportOperatorFactory exportTxtFileOperatorFactory = new ExportTxtFileOperatorFactory();
        exportTxtFileOperatorFactory.export("20190802数据......");
        // 创建提供导出文件到数据库脚本的 API 的对象
        AbstractExportOperatorFactory exportDBOperatorFacorty = new ExportDBOperatorFacorty();
        exportDBOperatorFacorty.export("20200729数据......");
    }
}
