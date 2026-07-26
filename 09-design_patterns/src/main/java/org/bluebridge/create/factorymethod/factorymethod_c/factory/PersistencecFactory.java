package org.bluebridge.create.factorymethod.factorymethod_c.factory;

import org.bluebridge.create.factorymethod.factorymethod_c.service.IUserService;

/**
 * 持久化工厂抽象类
 *
 * @author lingwh
 * @date 2026/7/22 10:15
 */
public abstract class PersistencecFactory {

    public void operate(Integer id) {
        IUserService operator = getOperator();
        operator.get(id);
    }

    abstract IUserService getOperator();
}
