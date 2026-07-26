package org.bluebridge.structure.adapter.adapter_e.service;

import org.bluebridge.structure.adapter.adapter_e.adapter.AbstractXxjlAdapter;
import org.bluebridge.structure.adapter.adapter_e.dao.GatzlDao;
import org.bluebridge.structure.adapter.adapter_e.dao.TztgDao;
import org.bluebridge.structure.adapter.adapter_e.dao.XxjlDao;
import org.bluebridge.structure.adapter.adapter_e.domain.Xxjl;

/**
 * 信息交流适配器服务
 *
 * @author lingwh
 * @date 2026/7/22 20:43
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
