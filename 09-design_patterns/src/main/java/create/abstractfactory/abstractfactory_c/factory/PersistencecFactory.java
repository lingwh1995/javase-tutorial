package create.abstractfactory.abstractfactory_c.factory;

import create.abstractfactory.abstractfactory_c.service.ICustomerService;
import create.abstractfactory.abstractfactory_c.service.IUserService;

public interface PersistencecFactory {
    IUserService getUserOperator();

    ICustomerService getCustomerOperator();
}
