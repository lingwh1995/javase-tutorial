package create.factorymethod.factorymethod_c.factory;

import create.factorymethod.factorymethod_c.service.IUserService;

/**
 * 持久化工厂抽象类
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class PersistencecFactory {

    public void operate(Integer id) {
        IUserService operator = getOperator();
        operator.get(id);
    }

    abstract IUserService getOperator();
}
