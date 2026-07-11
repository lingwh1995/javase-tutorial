package create.abstractfactory.abstractfactory_d.service;

import create.abstractfactory.abstractfactory_d.domain.User;

/**
 * @author lingwh
 * @desc 用户服务接口
 * @date 2026/7/9 00:00
 */
public interface IUserService {
    User get(Integer uid);
}
