package structure.adapter.adapter_f.adapter;

import structure.adapter.adapter_f.domain.Gatzl;
import structure.adapter.adapter_f.domain.Tztg;
import structure.adapter.adapter_f.service.IGatzlService;
import structure.adapter.adapter_f.service.ITztgService;

/**
 * @author lingwh
 * @desc 信息交流适配器抽象类
 * @date 2026/7/9 00:00
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
