package create.abstractfactory.abstractfactory_c.client;

import create.abstractfactory.abstractfactory_c.domain.User;
import create.abstractfactory.abstractfactory_c.factory.MysqlFactory;
import create.abstractfactory.abstractfactory_c.factory.OracelFactory;
import create.abstractfactory.abstractfactory_c.service.ICustomerService;
import create.abstractfactory.abstractfactory_c.service.IUserService;
import create.abstractfactory.abstractfactory_c.service.UserServiceMysql;
import create.abstractfactory.abstractfactory_c.service.UserServiceOracle;

/**
 * 测试抽象工厂模式
 *
 * 测试抽象工厂模式与工厂方法模式区别：抽象工厂模式可以产生多个对象，工厂方法模式只能产生一个对象
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        // 不使用工厂方法模式
        UserServiceOracle userServiceOracle = new UserServiceOracle();
        User oracle = userServiceOracle.get(1);

        UserServiceMysql userServiceMysql = new UserServiceMysql();
        User mysql = userServiceMysql.get(1);

        System.out.println("--------------------------------------------");

        // 使用抽象工厂方法模式
        OracelFactory oracelFactory = OracelFactory.class.newInstance();
        MysqlFactory mysqlFactory = MysqlFactory.class.newInstance();

        // 操作User表
        IUserService userOracleOperator = oracelFactory.getUserOperator();
        userOracleOperator.get(1);
        IUserService userMysqlOperator = mysqlFactory.getUserOperator();
        userMysqlOperator.get(1);

        // 操作Customer表
        ICustomerService customerMysqlOperator = mysqlFactory.getCustomerOperator();
        ICustomerService customerOracleOperator = oracelFactory.getCustomerOperator();
        customerMysqlOperator.get(1);
        customerOracleOperator.get(1);
    }
}
