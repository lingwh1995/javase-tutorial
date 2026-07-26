package org.bluebridge.create.abstractfactory.abstractfactory_c.client;

import org.bluebridge.create.abstractfactory.abstractfactory_c.factory.EasyPersistenceFactory;
import org.bluebridge.create.abstractfactory.abstractfactory_c.factory.MysqlFactory;
import org.bluebridge.create.abstractfactory.abstractfactory_c.factory.PersistencecFactory;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.ICustomerService;
import org.bluebridge.create.abstractfactory.abstractfactory_c.service.IUserService;

/**
 * 测试抽象工厂+简单工厂(保留该产品的抽象工厂和具体抽象工厂的实现类)
 *
 * @author lingwh
 * @date 2026/7/22 17:22
 */
public class EasyPersistenceFactoryClient {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // 从简单工厂中创建具体的工厂类
        // Mysql 工厂
        PersistencecFactory operatorFactory = EasyPersistenceFactory.getOperator(MysqlFactory.class);
        // Oracle 工厂
        // PersistencecFactory operatorFactory =
        // EasyPersistenceFactory.getOperator(OracelFactory.class);
        // 从具体的工厂中创建操作者
        IUserService userOperator = operatorFactory.getUserOperator();
        ICustomerService customerOperator = operatorFactory.getCustomerOperator();
        // 执行具体的操做
        userOperator.get(1);
        customerOperator.get(1);
    }
}
