package create.abstractfactory.abstractfactory_l;

public class MysqlUser implements IUser {
    @Override
    public void insert(User user) {
        System.out.println("在MySQL数据库中给User表增加了一条记录");
    }

    @Override
    public User getUser(int id) {
        System.out.println("在MySQL数据库中根据ID得到了User表一条记录");
        return null;
    }
}
