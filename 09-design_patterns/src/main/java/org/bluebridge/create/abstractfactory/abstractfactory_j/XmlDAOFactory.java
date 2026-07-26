package org.bluebridge.create.abstractfactory.abstractfactory_j;

/**
 * XML 方式 DAO 工厂
 *
 * @author lingwh
 * @date 2019/9/4 13:13
 */
public class XmlDAOFactory extends DAOFactory {

    @Override
    public OrderDetailDAO createOrderDetailDAO() {
        return new XmlDetailDAOImpl();
    }

    @Override
    public OrderMainDAO createOrderMainDAO() {
        return new XmlMainDAOImpl();
    }
}
