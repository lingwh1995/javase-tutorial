package create.abstractfactory.abstractfactory_d.service;

import create.abstractfactory.abstractfactory_d.domain.User;

/**
 * @author lingwh
 * @desc Mysql用户服务实现
 * @date 2026/7/9 00:00
 */
public class UserServiceMysql implements IUserService {

    @Override
    public User get(Integer uid) {
        System.out.println("Mysql操作数据库......User");
        return new User();
    }
}
