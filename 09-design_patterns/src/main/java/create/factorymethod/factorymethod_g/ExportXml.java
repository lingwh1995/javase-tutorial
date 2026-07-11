package create.factorymethod.factorymethod_g;

/**
 * @author lingwh
 * @desc 导出Xml
 * @date 2019/9/3 17:07
 */
public class ExportXml implements ExportFileApi {

    /**
     * 导出成为文件
     *
     * @param data 需要保存的数据
     * @return
     */
    @Override
    public boolean export(String data) {
        System.out.println("导出" + data + "xml方式......");
        return true;
    }
}
