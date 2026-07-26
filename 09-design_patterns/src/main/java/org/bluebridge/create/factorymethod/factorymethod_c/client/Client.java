package org.bluebridge.create.factorymethod.factorymethod_c.client;

import org.bluebridge.create.factorymethod.factorymethod_c.domain.User;
import org.bluebridge.create.factorymethod.factorymethod_c.factory.MysqlFactory;
import org.bluebridge.create.factorymethod.factorymethod_c.factory.OracelFactory;
import org.bluebridge.create.factorymethod.factorymethod_c.factory.PersistencecFactory;
import org.bluebridge.create.factorymethod.factorymethod_c.service.IUserService;
import org.bluebridge.create.factorymethod.factorymethod_c.service.UserServiceMysql;
import org.bluebridge.create.factorymethod.factorymethod_c.service.UserServiceOracle;

/**
 * 工厂方法模式客户端
 *
 * @author lingwh
 * @date 2026/7/22 11:28
 */
public class Client {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        // 不使用工厂方法模式
        IUserService userServiceOracle = new UserServiceOracle();
        User oracle = userServiceOracle.get(1);

        IUserService userServiceMysql = new UserServiceMysql();
        User mysql = userServiceMysql.get(1);

        System.out.println("--------------------------------------------");

        // 使用工厂方法模式
        PersistencecFactory oracelFactory = new OracelFactory();
        oracelFactory.operate(1);

        PersistencecFactory mysqllFactory = new MysqlFactory();
        mysqllFactory.operate(1);
    }
}
