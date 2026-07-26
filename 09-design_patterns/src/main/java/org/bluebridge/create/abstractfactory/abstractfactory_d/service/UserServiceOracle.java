package org.bluebridge.create.abstractfactory.abstractfactory_d.service;

import org.bluebridge.create.abstractfactory.abstractfactory_d.domain.User;

/**
 * Oracle 用户服务实现
 *
 * @author lingwh
 * @date 2026/7/22 14:08
 */
public class UserServiceOracle implements IUserService {

    @Override
    public User get(Integer uid) {
        System.out.println("Oracel操作数据库......User");
        return new User();
    }
}
