package org.bluebridge.create.factorymethod.factorymethod_c.factory;

import org.bluebridge.create.factorymethod.factorymethod_c.service.IUserService;
import org.bluebridge.create.factorymethod.factorymethod_c.service.UserServiceMysql;

/**
 * Mysql 工厂
 *
 * @author lingwh
 * @date 2026/7/22 08:33
 */
public class MysqlFactory extends PersistencecFactory {

    @Override
    public IUserService getOperator() {
        return new UserServiceMysql();
    }
}
