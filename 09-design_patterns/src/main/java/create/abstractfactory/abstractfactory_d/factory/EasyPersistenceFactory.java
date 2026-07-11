package create.abstractfactory.abstractfactory_d.factory;

import create.abstractfactory.abstractfactory_d.service.*;

/**
 * @author lingwh
 * @desc 简单工厂和抽象工厂模式
 * @date 2026/7/9 00:00
 */
public class EasyPersistenceFactory {
    private static String db = "MySQL";

    public static IUserService createUserOperator() {
        IUserService userService = null;
        switch (db) {
            case "MySQL":
                userService = new UserServiceMysql();
                break;

            case "Oracle":
                userService = new UserServiceOracle();
                break;
        }
        return userService;
    }

    public static ICustomerService createCustomerOperator() {
        ICustomerService customerService = null;
        switch (db) {
            case "MySQL":
                customerService = new CustomerServiceMysql();
                break;

            case "Oracle":
                customerService = new CustomerServiceOracle();
                break;
        }
        return customerService;
    }
}
