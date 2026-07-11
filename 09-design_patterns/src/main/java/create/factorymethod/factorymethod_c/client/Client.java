package create.factorymethod.factorymethod_c.client;

import create.factorymethod.factorymethod_c.domain.User;
import create.factorymethod.factorymethod_c.factory.MysqlFactory;
import create.factorymethod.factorymethod_c.factory.OracelFactory;
import create.factorymethod.factorymethod_c.factory.PersistencecFactory;
import create.factorymethod.factorymethod_c.service.IUserService;
import create.factorymethod.factorymethod_c.service.UserServiceMysql;
import create.factorymethod.factorymethod_c.service.UserServiceOracle;

/**
 * @author lingwh
 * @desc 工厂方法模式客户端
 * @date 2026/7/9 00:00
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
