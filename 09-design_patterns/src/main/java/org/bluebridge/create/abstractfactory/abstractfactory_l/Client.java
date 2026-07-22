package org.bluebridge.create.abstractfactory.abstractfactory_l;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/9/4 13:25
 */
public class Client {

    public static void main(String[] args) {
        // 不使用设计模式直接获取用于操作的接口对象
        IUser mysqlUser = new MysqlUser();
        mysqlUser.insert(new User());
        mysqlUser.getUser(1);
        IOrder mysqlOrder = new MysqlOrder();
        mysqlOrder.insert(new Order());
        mysqlOrder.getOrder(1);

        IUser oracleUser = new OracleUser();
        oracleUser.insert(new User());
        oracleUser.getUser(1);
        IOrder oracleOrder = new OracleOrder();
        oracleOrder.insert(new Order());
        oracleOrder.getOrder(1);

        System.out.println("--------------------------------------");
        // 使用了抽象工厂模式后，把创建操作接口的细节隐藏了起来，不用new了，直接从工厂中获取
        IFactory mysqlFactory = new MysqlFactory();
        IUser userInterfaceMysql = mysqlFactory.createUserInterface();
        userInterfaceMysql.insert(new User());
        userInterfaceMysql.getUser(1);
        IOrder orderInterfaceMysql = mysqlFactory.createOrderInterface();
        orderInterfaceMysql.insert(new Order());
        orderInterfaceMysql.getOrder(1);

        IFactory oracleFactory = new OracleFactory();
        IUser userInterfaceOracle = oracleFactory.createUserInterface();
        userInterfaceOracle.insert(new User());
        userInterfaceOracle.getUser(1);
        IOrder orderInterfaceOracle = oracleFactory.createOrderInterface();
        orderInterfaceOracle.insert(new Order());
        orderInterfaceOracle.getOrder(1);
    }
}
