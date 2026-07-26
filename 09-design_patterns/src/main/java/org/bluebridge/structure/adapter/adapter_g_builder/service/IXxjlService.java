package org.bluebridge.structure.adapter.adapter_g_builder.service;

import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * 信息交流接口
 *
 * @author lingwh
 * @date 2026/7/22 10:18
 */
public interface IXxjlService {

    void save(Xxjl xxjl) throws Exception;

    void delete(Xxjl xxjl) throws Exception;
}
