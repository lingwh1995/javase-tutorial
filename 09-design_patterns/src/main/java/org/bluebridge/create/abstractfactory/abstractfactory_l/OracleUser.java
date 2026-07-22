package org.bluebridge.create.abstractfactory.abstractfactory_l;

/**
 * OracleUser
 *
 * @author lingwh
 * @date 2025/11/10 11:46
 */
public class OracleUser implements IUser {

    @Override
    public void insert(User user) {
        System.out.println("在Oracle数据库中给User表增加了一条记录");
    }

    @Override
    public User getUser(int id) {
        System.out.println("在Oracle数据库中根据ID得到了User表一条记录");
        return null;
    }
}
