package create.builder.builder_j;

import java.util.Collection;
import java.util.Map;

/**
 * 文本构建器
 *
 * @author lingwh
 * @date 2019/8/8 14:21
 */
public class TxtBuilder extends AbstractBuilder {

    @Override
    public void buildHeader(ExportHeaderModel ehm) {
        super.buffer.append(ehm.getDepId() + "," + ehm.getExportDate() + "\n");
    }

    @Override
    public void buildBody(Map<String, Collection<ExportDataModel>> mapData) {
        for (String tblName : mapData.keySet()) {
            // 先拼接表名称
            super.buffer.append(tblName + "\n");
            // 然后循环拼接具体数据
            for (ExportDataModel edm : mapData.get(tblName)) {
                super.buffer.append(
                        edm.getProductId() + "," + edm.getPrice() + "," + edm.getAmount() + "\n");
            }
        }
    }

    @Override
    public void buildFooter(ExportFooterModel efm) {
        super.buffer.append(efm.getExportUser());
    }
}
