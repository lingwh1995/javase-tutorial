package org.bluebridge.create.abstractfactory.abstractfactory_j;

/**
 * 抽象工厂客户端
 *
 * @author lingwh
 * @date 2019/9/4 13:25
 */
public class Client {

    public static void main(String[] args) {
        // 创建 DAO 的抽象工厂
        DAOFactory df = new RdbDAOFactory();
        // 通过抽象工厂来获取需要的 DAO 接口
        OrderMainDAO mainDAO = df.createOrderMainDAO();
        OrderDetailDAO detailDAO = df.createOrderDetailDAO();
        // 调用 DAO 来完成数据存储的功能
        mainDAO.saveOrderMain();
        detailDAO.saveOrderDetail();
    }
}
