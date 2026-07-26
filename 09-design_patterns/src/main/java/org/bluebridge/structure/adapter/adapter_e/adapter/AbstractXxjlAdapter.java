package org.bluebridge.structure.adapter.adapter_e.adapter;

import org.bluebridge.structure.adapter.adapter_e.domain.Xxjl;
import org.bluebridge.structure.adapter.adapter_e.service.IXxjlService;

/**
 * 抽象信息交流适配器
 *
 * @author lingwh
 * @date 2026/7/22 09:45
 */
public class AbstractXxjlAdapter implements IXxjlService {

    @Override
    public void save(Xxjl xxjl) {}

    @Override
    public boolean delete(Xxjl xxjl) {
        return false;
    }
}
