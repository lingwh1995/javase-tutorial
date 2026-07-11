package create.abstractfactory.abstractfactory_e;

/**
 * @author lingwh
 * @desc 文本文件操作者工厂
 * @date 2019/8/2 10:28
 */
public class TxtFileOperatorFactory extends AbstractFileOperatorFactory {

    @Override
    public FileOperator createFileOperator() {
        return new TxtFileOperator();
    }
}
