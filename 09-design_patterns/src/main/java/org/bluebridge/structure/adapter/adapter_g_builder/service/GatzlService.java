package org.bluebridge.structure.adapter.adapter_g_builder.service;

import org.bluebridge.structure.adapter.adapter_g_builder.dao.GatzlDao;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Gatzl;

/**
 * 公安厅指令服务实现
 *
 * @author lingwh
 * @date 2026/7/22 14:33
 */
public class GatzlService implements IGatzlService {

    private GatzlDao gatzlDao = new GatzlDao();

    @Override
    public void save(Gatzl gatzl) {
        gatzlDao.save(gatzl);
    }

    @Override
    public boolean delete(Gatzl gatzl) {
        return gatzlDao.delete(gatzl);
    }

    @Override
    public boolean sendMsg(String phoneNumber) {
        return gatzlDao.sendMsg(phoneNumber);
    }
}
