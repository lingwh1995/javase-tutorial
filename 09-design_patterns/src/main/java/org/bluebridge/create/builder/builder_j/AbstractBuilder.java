package org.bluebridge.create.builder.builder_j;

import java.util.Collection;
import java.util.Map;

/**
 * 生成器接口，定义创建一个输出文件对象所需的各个部件的操作
 *
 * @author lingwh
 * @date 2019/8/8 14:20
 */
public abstract class AbstractBuilder {

    /**
     * 用来记录构建的文件的内容，相当于产品
     */
    protected StringBuffer buffer = new StringBuffer();

    /**
     * 构建输出文件的 Header 部分
     *
     * @param ehm 文件头的内容
     */
    abstract void buildHeader(ExportHeaderModel ehm);

    /**
     * 构建输出文件的 Body 部分
     *
     * @param mapData 要输出的数据的内容
     */
    abstract void buildBody(Map<String, Collection<ExportDataModel>> mapData);

    /**
     * 构建输出文件的 Footer 部分
     *
     * @param efm 文件尾的内容
     */
    abstract void buildFooter(ExportFooterModel efm);

    public StringBuffer getResult() {
        return buffer;
    }
}
