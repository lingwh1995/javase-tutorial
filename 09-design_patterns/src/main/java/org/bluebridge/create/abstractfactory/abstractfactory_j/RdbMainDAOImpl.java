package org.bluebridge.create.abstractfactory.abstractfactory_j;

/**
 * 关系型数据库操作主订单
 *
 * @author lingwh
 * @date 2019/9/4 11:36
 */
public class RdbMainDAOImpl implements OrderMainDAO {

    @Override
    public void saveOrderMain() {
        System.out.println("now in RdbMainDAOImpl saveOrderMain");
    }
}
