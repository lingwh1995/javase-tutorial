package create.factorymethod.factorymethod_c.factory;

import create.factorymethod.factorymethod_c.service.IUserService;
import create.factorymethod.factorymethod_c.service.UserServiceOracle;

/**
 * @author lingwh
 * @desc Oracle工厂
 * @date 2026/7/9 00:00
 */
public class OracelFactory extends PersistencecFactory {
    @Override
    public IUserService getOperator() {
        return new UserServiceOracle();
    }
}
