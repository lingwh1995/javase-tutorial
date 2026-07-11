package create.factorymethod.factorymethod_c.factory;

import create.factorymethod.factorymethod_c.service.IUserService;

/**
 * @author lingwh
 * @desc 持久化工厂抽象类
 * @date 2026/7/9 00:00
 */
public abstract class PersistencecFactory {
    public void operate(Integer id) {
        IUserService operator = getOperator();
        operator.get(id);
    }

    abstract IUserService getOperator();
}
