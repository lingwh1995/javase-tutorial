package create.abstractfactory.abstractfactory_j;

/**
 * @author lingwh
 * @desc 关系型数据库工厂
 * @date 2019/9/4 11:42
 */
public class RdbDAOFactory extends DAOFactory {

    @Override
    public OrderDetailDAO createOrderDetailDAO() {
        return new RdbDetailDAOImpl();
    }

    @Override
    public OrderMainDAO createOrderMainDAO() {
        return new RdbMainDAOImpl();
    }
}
