package org.bluebridge.structure.adapter.adapter_f.adapter;

import org.bluebridge.structure.adapter.adapter_f.domain.Gatzl;
import org.bluebridge.structure.adapter.adapter_f.domain.Tztg;
import org.bluebridge.structure.adapter.adapter_f.service.IGatzlService;
import org.bluebridge.structure.adapter.adapter_f.service.ITztgService;

/**
 * 信息交流适配器抽象类
 *
 * @author lingwh
 * @date 2026/7/22 21:15
 */
public class AbstractXxjlAdapter implements IGatzlService, ITztgService {

    @Override
    public void save(Gatzl gatzl) {
    }

    @Override
    public boolean delete(Gatzl gatzl) {
        return false;
    }

    @Override
    public boolean sendMsg(String phoneNumber) {
        return false;
    }

    @Override
    public void save(Tztg tztg) {
    }

    @Override
    public boolean delete(Tztg tztg) {
        return false;
    }

    @Override
    public boolean sendEmail(String email) {
        return false;
    }
}
