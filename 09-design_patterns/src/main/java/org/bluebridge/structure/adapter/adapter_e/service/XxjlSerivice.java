package org.bluebridge.structure.adapter.adapter_e.service;

import org.bluebridge.structure.adapter.adapter_e.dao.XxjlDao;
import org.bluebridge.structure.adapter.adapter_e.domain.Xxjl;

/**
 * 信息交流服务实现
 *
 * @author lingwh
 * @date 2026/7/22 19:27
 */
public class XxjlSerivice implements IXxjlService {

    private XxjlDao xxjlDao = new XxjlDao();

    @Override
    public void save(Xxjl xxjl) {
        xxjlDao.save(xxjl);
    }

    @Override
    public boolean delete(Xxjl xxjl) {
        xxjlDao.delete(xxjl);
        return false;
    }
}
