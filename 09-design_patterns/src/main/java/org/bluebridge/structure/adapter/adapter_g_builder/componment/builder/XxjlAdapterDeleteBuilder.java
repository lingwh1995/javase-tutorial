package org.bluebridge.structure.adapter.adapter_g_builder.componment.builder;

import org.bluebridge.structure.adapter.adapter_g_builder.dao.*;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * XxjlAdapter实际构建者
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlAdapterDeleteBuilder extends XxjlAdapterBuilder {

    private XxjlFjrDao xxjlFjrDao = new XxjlFjrDao();
    private XxjlSjrDao xxjlSjrDao = new XxjlSjrDao();

    @Override
    public XxjlAdapterBuilder buildXxjlFjrDao(Xxjl xxjl) {
        xxjlFjrDao.delete(xxjl);
        return this;
    }

    @Override
    public XxjlAdapterBuilder buildXxjlSjrDao(Xxjl xxjl) {
        xxjlSjrDao.delete(xxjl);
        return this;
    }
}
