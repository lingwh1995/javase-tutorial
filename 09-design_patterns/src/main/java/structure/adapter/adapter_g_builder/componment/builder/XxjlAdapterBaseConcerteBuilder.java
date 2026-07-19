package structure.adapter.adapter_g_builder.componment.builder;

import structure.adapter.adapter_g_builder.dao.GatzlDao;
import structure.adapter.adapter_g_builder.dao.TztgDao;
import structure.adapter.adapter_g_builder.dao.XxjlOpenfireDao;
import structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * Xxjl适配器基础具体构建者
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlAdapterBaseConcerteBuilder extends XxjlAdapterBaseBuilder {

    private XxjlOpenfireDao xxjlOpenfireDao = new XxjlOpenfireDao();
    private GatzlDao gtzlDao = new GatzlDao();
    private TztgDao tztgDao = new TztgDao();

    @Override
    public XxjlAdapterBaseBuilder buildXxjlOpenfireDao(Xxjl xxjl) {
        xxjlOpenfireDao.sendXxjlNotice(xxjl);
        return this;
    }

    @Override
    public XxjlAdapterBaseBuilder buildGatzlDao(String phoneNumber) {
        gtzlDao.sendMsg(phoneNumber);
        return this;
    }

    @Override
    public XxjlAdapterBaseBuilder buildTztgDao(String email) {
        tztgDao.sendEmail(email);
        return this;
    }
}
