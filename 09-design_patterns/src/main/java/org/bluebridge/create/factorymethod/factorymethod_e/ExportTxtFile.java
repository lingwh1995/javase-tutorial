package org.bluebridge.create.factorymethod.factorymethod_e;

/**
 * 提供导出文件到文本文档 API 的对象，本质上是一个 API 的提供者
 *
 * @author lingwh
 * @date 2019/8/2 9:30
 */
public class ExportTxtFile implements ExportFileApi {

    /**
     * 导出文件到文本文件
     *
     * @param data 具体要导出的数据
     * @return
     */
    @Override
    public boolean export(String data) {
        System.out.println("导出" + data + "文本文件......");
        return false;
    }
}
