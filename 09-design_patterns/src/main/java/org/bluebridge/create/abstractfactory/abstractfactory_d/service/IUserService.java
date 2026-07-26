package org.bluebridge.create.abstractfactory.abstractfactory_d.service;

import org.bluebridge.create.abstractfactory.abstractfactory_d.domain.User;

/**
 * 用户服务接口
 *
 * @author lingwh
 * @date 2026/7/22 11:42
 */
public interface IUserService {

    User get(Integer uid);
}
