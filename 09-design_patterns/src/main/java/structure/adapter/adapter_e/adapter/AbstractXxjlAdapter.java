package structure.adapter.adapter_e.adapter;

import structure.adapter.adapter_e.domain.Xxjl;
import structure.adapter.adapter_e.service.IXxjlService;

public class AbstractXxjlAdapter implements IXxjlService {

    @Override
    public void save(Xxjl xxjl) {}

    @Override
    public boolean delete(Xxjl xxjl) {
        return false;
    }
}
