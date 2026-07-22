package org.bluebridge.create.abstractfactory.abstractfactory_d.service;

import org.bluebridge.create.abstractfactory.abstractfactory_d.domain.User;

/**
 * 用户服务接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface IUserService {

    User get(Integer uid);
}
