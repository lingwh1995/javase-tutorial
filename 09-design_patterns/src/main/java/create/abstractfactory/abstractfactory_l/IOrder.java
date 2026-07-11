package create.abstractfactory.abstractfactory_l;

/**
 * Order接口
 *
 * @author lingwh
 * @date 2019/8/7 14:57
 */
public interface IOrder {
    void insert(Order order);

    Order getOrder(int id);
}
