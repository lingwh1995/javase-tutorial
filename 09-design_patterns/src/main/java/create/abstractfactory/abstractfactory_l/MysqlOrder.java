package create.abstractfactory.abstractfactory_l;

public class MysqlOrder implements IOrder {
    @Override
    public void insert(Order order) {
        System.out.println("在MySQL数据库中给Order表增加了一条记录");
    }

    @Override
    public Order getOrder(int id) {
        System.out.println("在MySQL数据库中根据ID得到了Order表一条记录");
        return null;
    }
}
