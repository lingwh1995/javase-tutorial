package create.factorymethod.factorymethod_c.factory;

import create.factorymethod.factorymethod_c.service.IUserService;
import create.factorymethod.factorymethod_c.service.UserServiceMysql;

/**
 * Mysql工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class MysqlFactory extends PersistencecFactory {

    @Override
    public IUserService getOperator() {
        return new UserServiceMysql();
    }
}
