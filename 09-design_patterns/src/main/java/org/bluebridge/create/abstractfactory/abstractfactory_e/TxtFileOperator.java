package org.bluebridge.create.abstractfactory.abstractfactory_e;

/**
 * 提供导出文件到文本文档 API 的对象，本质上是一个 API 的提供者
 *
 * @author lingwh
 * @date 2019/8/2 9:30
 */
public class TxtFileOperator implements FileOperator {

    /**
     * 导出方法
     *
     * @param data 具体要导出的数据
     * @return
     */
    @Override
    public boolean exportFile(String data) {
        System.out.println("导出" + data + "到文本文档......");
        return false;
    }

    @Override
    public boolean importFile(String data) {
        System.out.println("从文本文档中导入" + data + "......");
        return false;
    }
}
