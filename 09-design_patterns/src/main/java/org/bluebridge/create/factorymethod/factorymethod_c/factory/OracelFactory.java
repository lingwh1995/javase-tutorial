package org.bluebridge.create.factorymethod.factorymethod_c.factory;

import org.bluebridge.create.factorymethod.factorymethod_c.service.IUserService;
import org.bluebridge.create.factorymethod.factorymethod_c.service.UserServiceOracle;

/**
 * Oracle 工厂
 *
 * @author lingwh
 * @date 2026/7/22 09:47
 */
public class OracelFactory extends PersistencecFactory {

    @Override
    public IUserService getOperator() {
        return new UserServiceOracle();
    }
}
