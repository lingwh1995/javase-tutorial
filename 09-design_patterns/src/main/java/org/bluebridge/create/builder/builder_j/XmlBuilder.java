package org.bluebridge.create.builder.builder_j;

import java.util.Collection;
import java.util.Map;

/**
 * 实现导出数据到 XML 文件的的生成器对象
 *
 * @author lingwh
 * @date 2019/8/8 14:26
 */
public class XmlBuilder extends AbstractBuilder {

    @Override
    public void buildHeader(ExportHeaderModel ehm) {
        super.buffer.append("<?xml version='1.0' encoding='gb2312'?>\n");
        super.buffer.append("<Report>\n");
        super.buffer.append("  <Header>\n");
        super.buffer.append("    <DepId>" + ehm.getDepId() + "</DepId>\n");
        super.buffer.append("    <ExportDate>" + ehm.getExportDate() + "</ExportDate>\n");
        super.buffer.append("  </Header>\n");
    }

    @Override
    public void buildBody(Map<String, Collection<ExportDataModel>> mapData) {
        super.buffer.append("  <Body>\n");
        for (String tblName : mapData.keySet()) {
            // 先拼接表名称
            super.buffer.append("    <Datas TableName=\"" + tblName + "\">\n");
            // 然后循环拼接具体数据
            for (ExportDataModel edm : mapData.get(tblName)) {
                super.buffer.append("      <Data>\n");
                super.buffer.append("        <ProductId>" + edm.getProductId() + "</ProductId>\n");
                super.buffer.append("        <Price>" + edm.getPrice() + "</Price>\n");
                super.buffer.append("        <Amount>" + edm.getAmount() + "</Amount>\n");
                super.buffer.append("      </Data>\n");
            }
            super.buffer.append("    </Datas>\n");
        }
        super.buffer.append("  </Body>\n");
    }

    @Override
    public void buildFooter(ExportFooterModel efm) {
        super.buffer.append("  <Footer>\n");
        super.buffer.append("    <ExportUser>" + efm.getExportUser() + "</ExportUser>\n");
        super.buffer.append("  </Footer>\n");
        super.buffer.append("</Report>\n");
    }
}
