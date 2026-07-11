package structure.adapter.adapter_g_builder.service;

import structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * 信息交流接口
 *
 * @author lingwh
 * @date
 */
public interface IXxjlService {
    void save(Xxjl xxjl) throws Exception;

    void delete(Xxjl xxjl) throws Exception;
}
