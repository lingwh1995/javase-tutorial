package create.factorymethod.factorymethod_c.service;

import create.factorymethod.factorymethod_c.domain.User;

/**
 * @author lingwh
 * @desc Mysql用户服务
 * @date 2026/7/9 00:00
 */
public class UserServiceMysql implements IUserService {

    @Override
    public User get(Integer uid) {
        System.out.println("Mysql操作数据库......");
        return new User();
    }
}
