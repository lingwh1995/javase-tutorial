package org.bluebridge.structure.adapter.adapter_g_builder.service;

import org.bluebridge.structure.adapter.adapter_g_builder.dao.TztgDao;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Tztg;

/**
 * 通知通告服务实现
 *
 * @author lingwh
 * @date 2026/7/22 12:47
 */
public class TztgService implements ITztgService {

    private TztgDao tztgDao = new TztgDao();

    @Override
    public void save(Tztg tztg) {
        tztgDao.save(tztg);
    }

    @Override
    public boolean delete(Tztg tztg) {
        return tztgDao.delete(tztg);
    }

    @Override
    public boolean sendEmail(String email) {
        return tztgDao.sendEmail(email);
    }
}
