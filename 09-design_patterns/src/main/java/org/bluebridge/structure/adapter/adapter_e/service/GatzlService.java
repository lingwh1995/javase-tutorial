package org.bluebridge.structure.adapter.adapter_e.service;

import org.bluebridge.structure.adapter.adapter_e.dao.GatzlDao;
import org.bluebridge.structure.adapter.adapter_e.domain.Gatzl;

/**
 * 公安厅指令 Service
 *
 * @author lingwh
 * @date 2026/7/22 18:12
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
