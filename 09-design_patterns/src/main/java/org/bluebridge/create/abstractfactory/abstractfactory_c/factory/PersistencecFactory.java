package org.bluebridge.create.abstractfactory.abstractfactory_c.factory;

import create.abstractfactory.abstractfactory_c.service.ICustomerService;
import create.abstractfactory.abstractfactory_c.service.IUserService;

/**
 * PersistencecFactory
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public interface PersistencecFactory {

    IUserService getUserOperator();

    ICustomerService getCustomerOperator();
}
