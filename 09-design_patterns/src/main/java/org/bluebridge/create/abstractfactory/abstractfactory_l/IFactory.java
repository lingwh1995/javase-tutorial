package org.bluebridge.create.abstractfactory.abstractfactory_l;

/**
 * 工厂接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface IFactory {

    IUser createUserInterface();

    IOrder createOrderInterface();
}
