package structure.adapter.adapter_g_builder.componment.adapter;

import structure.adapter.adapter_g_builder.domain.Gatzl;
import structure.adapter.adapter_g_builder.domain.Tztg;
import structure.adapter.adapter_g_builder.service.IGatzlService;
import structure.adapter.adapter_g_builder.service.ITztgService;

/**
 * 抽象信息交流适配器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AbstractXxjlAdapter implements IGatzlService, ITztgService {

    @Override
    public void save(Gatzl gatzl) {}

    @Override
    public boolean delete(Gatzl gatzl) {
        return false;
    }

    @Override
    public boolean sendMsg(String phoneNumber) {
        return false;
    }

    @Override
    public void save(Tztg tztg) {}

    @Override
    public boolean delete(Tztg tztg) {
        return false;
    }

    @Override
    public boolean sendEmail(String email) {
        return false;
    }
}
