package structure.adapter.adapter_e.service;

import structure.adapter.adapter_e.dao.XxjlDao;
import structure.adapter.adapter_e.domain.Xxjl;

/**
 * @author lingwh
 * @date 2026/7/9 00:00
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
