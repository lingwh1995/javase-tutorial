package org.bluebridge.create.abstractfactory.abstractfactory_f;

/**
 * 操作数据库脚本的工厂
 *
 * @author lingwh
 * @date 2019/8/2 10:29
 */
public class DBOperatorFacorty extends AbstractFileOperatorFactory {

    @Override
    public FileOperator createFileOperator() {
        return new DbFileOperator();
    }
}
