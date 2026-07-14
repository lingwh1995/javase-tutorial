package create.factorymethod.factorymethod_f;

/**
 * 导出数据库
 *
 * @author lingwh
 * @date 2019/9/3 16:47
 */
public class ExportDB implements ExportFileApi {

    /**
     * 导出内容成为文件
     *
     * @param data 示意：需要保存的数据
     * @return 是否导出成功
     */
    @Override
    public boolean export(String data) {
        System.out.println("导出数据" + data + "到数据库备份文件~");
        return true;
    }
}
