package create.abstractfactory.abstractfactory_l;

public class MysqlFactory implements IFactory {
    @Override
    public IUser createUserInterface() {
        return new MysqlUser();
    }

    @Override
    public IOrder createOrderInterface() {
        return new MysqlOrder();
    }
}
