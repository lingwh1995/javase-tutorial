package org.bluebridge.create.abstractfactory.abstractfactory_l;

/**
 * Oracle工厂
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class OracleFactory implements IFactory {

    @Override
    public IUser createUserInterface() {
        return new OracleUser();
    }

    @Override
    public IOrder createOrderInterface() {
        return new OracleOrder();
    }
}
