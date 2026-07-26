package org.bluebridge.create.abstractfactory.abstractfactory_d.service;

import org.bluebridge.create.abstractfactory.abstractfactory_d.domain.User;

/**
 * Mysql 用户服务实现
 *
 * @author lingwh
 * @date 2026/7/22 13:27
 */
public class UserServiceMysql implements IUserService {

    @Override
    public User get(Integer uid) {
        System.out.println("Mysql操作数据库......User");
        return new User();
    }
}
