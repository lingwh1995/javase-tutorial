package create.abstractfactory.abstractfactory_d.client;

import create.abstractfactory.abstractfactory_d.domain.User;
import create.abstractfactory.abstractfactory_d.factory.EasyPersistenceFactory;
import create.abstractfactory.abstractfactory_d.service.ICustomerService;
import create.abstractfactory.abstractfactory_d.service.IUserService;
import create.abstractfactory.abstractfactory_d.service.UserServiceMysql;
import create.abstractfactory.abstractfactory_d.service.UserServiceOracle;

/**
 * 测试抽象工厂模式
 *
 * 测试抽象工厂模式与工厂方法模式区别
 * 1. 抽象工厂模式可以产生多个对象，工厂方法模式只能产生一个对象。
 * 2. 测试抽象工厂 + 简单工厂(不保留该产品的抽象工厂和具体抽象工厂的实现类，只有一个简单工厂)
 *
 * @author lingwh
 * @date 2019/3/10 19:02
 */
public class Client {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        // 不使用工厂方法模式
        UserServiceOracle userServiceOracle = new UserServiceOracle();
        User oracle = userServiceOracle.get(1);

        UserServiceMysql userServiceMysql = new UserServiceMysql();
        User mysql = userServiceMysql.get(1);

        System.out.println("--------------------------------------------");

        // 使用抽象工厂方法(通过简单工厂改进过)模式
        IUserService userOperator = EasyPersistenceFactory.createUserOperator();
        // 操作User表
        userOperator.get(1);

        ICustomerService customerOperator = EasyPersistenceFactory.createCustomerOperator();
        // 操作Customer表
        customerOperator.get(1);
    }
}
