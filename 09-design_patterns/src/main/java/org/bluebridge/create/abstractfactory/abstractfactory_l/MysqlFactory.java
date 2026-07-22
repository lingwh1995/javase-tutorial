package org.bluebridge.create.abstractfactory.abstractfactory_l;

/**
 * Mysql 数据库工厂
 *
 * @author lingwh
 * @date 2026/7/13 8:35
 */
public class MysqlFactory implements IFactory {

    @Override
    public IUser createUserInterface() {
        return new MysqlUser();
    }

    @Override
    public IOrder createOrderInterface() {
        return new MysqlOrder();
    }
}
