package org.bluebridge.create.factorymethod.factorymethod_f;

/**
 * 导出的文件对象的接口
 *
 * @author lingwh
 * @date 2019/9/3 16:45
 */
public interface ExportFileApi {

    /**
     * 导出内容成为文件
     *
     * @param data 示意：需要保存的数据
     * @return 是否导出成功
     */
    public boolean export(String data);
}
