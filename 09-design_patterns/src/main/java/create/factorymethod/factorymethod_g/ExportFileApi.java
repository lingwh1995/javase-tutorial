package create.factorymethod.factorymethod_g;

/**
 * @author lingwh
 * @desc 导出文件接口
 * @date 2019/9/3 16:57
 */
public interface ExportFileApi {

    /**
     * 导出成为文件
     *
     * @param data 需要保存的数据
     * @return
     */
    public boolean export(String data);
}
