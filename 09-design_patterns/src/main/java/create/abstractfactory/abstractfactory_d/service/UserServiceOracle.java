package create.abstractfactory.abstractfactory_d.service;

import create.abstractfactory.abstractfactory_d.domain.User;

/**
 * Oracle用户服务实现
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class UserServiceOracle implements IUserService {

    @Override
    public User get(Integer uid) {
        System.out.println("Oracel操作数据库......User");
        return new User();
    }
}
