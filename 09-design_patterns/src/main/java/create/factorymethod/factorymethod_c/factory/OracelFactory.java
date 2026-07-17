package create.factorymethod.factorymethod_c.factory;

import create.factorymethod.factorymethod_c.service.IUserService;
import create.factorymethod.factorymethod_c.service.UserServiceOracle;

/**
 * Oracle工厂
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class OracelFactory extends PersistencecFactory {

    @Override
    public IUserService getOperator() {
        return new UserServiceOracle();
    }
}
