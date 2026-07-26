package org.bluebridge.structure.adapter.adapter_g_builder.componment.builder;

import org.bluebridge.structure.adapter.adapter_g_builder.componment.adapter.XxjlAdapter;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * XxjlAdapter 抽象执行者
 *
 * @author lingwh
 * @date 2026/7/22 16:45
 */
public abstract class XxjlAdapterBuilder {

    private XxjlAdapter xxjlAdapter = new XxjlAdapter();

    /**
     * 执行发件人 Dao 保存/删除 Xxjl 行为
     *
     * @param xxjl
     */
    public abstract XxjlAdapterBuilder buildXxjlFjrDao(Xxjl xxjl);

    /**
     * 执行收件人 Dao 保存/删除 Xxjl 行为
     *
     * @param xxjl
     */
    public abstract XxjlAdapterBuilder buildXxjlSjrDao(Xxjl xxjl);
}
