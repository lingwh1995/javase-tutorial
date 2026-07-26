package org.bluebridge.create.abstractfactory.abstractfactory_c.factory;

import org.bluebridge.create.abstractfactory.abstractfactory_c.service.CustomerServiceMysql;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.ICustomerService;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.IUserService;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.UserServiceMysql;

/**
 * Mysql 数据库工厂
 *
 * @author lingwh
 * @date 2026/7/22 09:12
 */
public class MysqlFactory implements PersistencecFactory {

    @Override
    public IUserService getUserOperator() {
        return new UserServiceMysql();
    }

    @Override
    public ICustomerService getCustomerOperator() {
        return new CustomerServiceMysql();
    }
}
