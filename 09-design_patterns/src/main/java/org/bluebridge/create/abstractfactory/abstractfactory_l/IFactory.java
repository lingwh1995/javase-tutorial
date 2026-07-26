package org.bluebridge.create.abstractfactory.abstractfactory_l;

/**
 * 工厂接口
 *
 * @author lingwh
 * @date 2026/7/22 08:33
 */
public interface IFactory {

    IUser createUserInterface();

    IOrder createOrderInterface();
}
