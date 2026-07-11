package structure.adapter.adapter_e.service;

import structure.adapter.adapter_e.adapter.AbstractXxjlAdapter;
import structure.adapter.adapter_e.dao.GatzlDao;
import structure.adapter.adapter_e.dao.TztgDao;
import structure.adapter.adapter_e.dao.XxjlDao;
import structure.adapter.adapter_e.domain.Xxjl;

/**
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlAdapterService extends AbstractXxjlAdapter {
    private GatzlDao gatzlDao = new GatzlDao();
    private TztgDao tztgDao = new TztgDao();
    private XxjlDao xxjlDao = new XxjlDao();

    @Override
    public void save(Xxjl xxjl) {
        xxjlDao.save(xxjl);
        gatzlDao.sendMsg("123456789");
        tztgDao.sendEmail("123@qq.com");
    }
}
