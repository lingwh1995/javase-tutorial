package create.abstractfactory.abstractfactory_j;

/**
 * 抽象工厂，创建订单主、子记录对应的DAO对象
 *
 * @author lingwh
 * @date 2019/9/4 11:34
 */
public abstract class DAOFactory {

    /**
     * 创建订单主记录对应的DAO对象
     *
     * @return 订单主记录对应的DAO对象
     */
    public abstract OrderMainDAO createOrderMainDAO();

    /**
     * 创建订单子记录对应的DAO对象
     *
     * @return 订单子记录对应的DAO对象
     */
    public abstract OrderDetailDAO createOrderDetailDAO();
}
