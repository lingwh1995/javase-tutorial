package create.factorymethod.factorymethod_e;

/**
 * 导出文本文件操作工厂
 *
 * @author lingwh
 * @date 2019/8/2 10:28
 */
public class ExportTxtFileOperatorFactory extends AbstractExportOperatorFactory {

    /**
     * 工厂方法，创建导出的文件对象的接口对象
     *
     * @return 导出的文件对象的接口对象
     */
    @Override
    protected ExportFileApi factoryMethod() {
        // 创建导出成文本文件格式的对象
        return new ExportTxtFile();
    }
}
