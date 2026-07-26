package org.bluebridge.create.abstractfactory.abstractfactory_c.factory;

import org.bluebridge.create.abstractfactory.abstractfactory_c.service.ICustomerService;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.IUserService;

/**
 * PersistencecFactory
 *
 * @author lingwh
 * @date 2026/7/22 13:45
 */
public interface PersistencecFactory {

    IUserService getUserOperator();

    ICustomerService getCustomerOperator();
}
