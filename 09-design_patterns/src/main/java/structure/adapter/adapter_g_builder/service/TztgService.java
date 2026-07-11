package structure.adapter.adapter_g_builder.service;

import structure.adapter.adapter_g_builder.dao.TztgDao;
import structure.adapter.adapter_g_builder.domain.Tztg;

/**
 * @author lingwh
 * @desc 通知通告服务实现
 * @date 2026/7/9 00:00
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
