package org.bluebridge.create.factorymethod.factorymethod_g;

/**
 * 导出数据：文本文件格式
 *
 * @author lingwh
 * @date 2019/9/3 16:59
 */
public class ExportTxtFile implements ExportFileApi {

    /**
     * 导出成为文件
     *
     * @param data 需要保存的数据
     * @return
     */
    @Override
    public boolean export(String data) {
        System.out.println("导出" + data + "文本文件形式......");
        return true;
    }
}
