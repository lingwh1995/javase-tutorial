package create.abstractfactory.abstractfactory_f;

/**
 * @author lingwh
 * @desc 操作文件的接口，本质上是一个,本质上是一个API的提供者的规范接口
 * @date 2019/8/2 9:29
 */
public interface FileOperator {

    /**
     * 导出方法
     *
     * @param data 具体要导出的数据
     * @return
     */
    boolean exportFile(String data);

    /**
     * 导入方法
     *
     * @param data
     * @return
     */
    boolean importFile(String data);
}
