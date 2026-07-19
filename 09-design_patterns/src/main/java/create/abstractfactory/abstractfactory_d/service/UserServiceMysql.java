package create.abstractfactory.abstractfactory_d.service;

import create.abstractfactory.abstractfactory_d.domain.User;

/**
 * Mysql用户服务实现
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class UserServiceMysql implements IUserService {

    @Override
    public User get(Integer uid) {
        System.out.println("Mysql操作数据库......User");
        return new User();
    }
}
