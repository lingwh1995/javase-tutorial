package org.bluebridge.create.abstractfactory.abstractfactory_c.factory;

import org.bluebridge.create.abstractfactory.abstractfactory_c.service.CustomerServiceOracle;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.ICustomerService;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.IUserService;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.UserServiceOracle;

/**
 * OracelFactory
 *
 * @author lingwh
 * @date 2026/7/22 11:27
 */
public class OracelFactory implements PersistencecFactory {

    @Override
    public IUserService getUserOperator() {
        return new UserServiceOracle();
    }

    @Override
    public ICustomerService getCustomerOperator() {
        return new CustomerServiceOracle();
    }
}
