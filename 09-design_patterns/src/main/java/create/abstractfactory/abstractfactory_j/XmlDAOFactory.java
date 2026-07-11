package create.abstractfactory.abstractfactory_j;

/**
 * @author lingwh
 * @desc XML方式DAO工厂
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
