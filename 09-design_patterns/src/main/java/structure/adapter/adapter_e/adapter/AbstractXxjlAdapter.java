package structure.adapter.adapter_e.adapter;

import structure.adapter.adapter_e.domain.Xxjl;
import structure.adapter.adapter_e.service.IXxjlService;

/**
 * 抽象信息交流适配器
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AbstractXxjlAdapter implements IXxjlService {

    @Override
    public void save(Xxjl xxjl) {}

    @Override
    public boolean delete(Xxjl xxjl) {
        return false;
    }
}
