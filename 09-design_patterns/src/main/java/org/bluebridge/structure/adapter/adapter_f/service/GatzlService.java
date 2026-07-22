package org.bluebridge.structure.adapter.adapter_f.service;

import org.bluebridge.structure.adapter.adapter_f.dao.GatzlDao;
import org.bluebridge.structure.adapter.adapter_f.domain.Gatzl;

/**
 * 公安厅指令服务实现
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
