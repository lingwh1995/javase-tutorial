package structure.adapter.adapter_e.service;

import structure.adapter.adapter_e.dao.GatzlDao;
import structure.adapter.adapter_e.domain.Gatzl;

/**
 * @author lingwh
 * @desc 公安厅指令Service
 * @date 2019/7/9 00:00
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
