package create.abstractfactory.abstractfactory_d.service;

import create.abstractfactory.abstractfactory_d.domain.Cusomer;

/**
 * @author lingwh
 * @desc 客户服务接口
 * @date 2026/7/9 00:00
 */
public interface ICustomerService {
    Cusomer get(Integer uid);
}
