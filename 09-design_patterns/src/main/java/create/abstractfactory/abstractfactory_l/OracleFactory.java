package create.abstractfactory.abstractfactory_l;

public class OracleFactory implements IFactory {
    @Override
    public IUser createUserInterface() {
        return new OracleUser();
    }

    @Override
    public IOrder createOrderInterface() {
        return new OracleOrder();
    }
}
