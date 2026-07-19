package structure.adapter.adapter_g_builder.componment.builder;

import structure.adapter.adapter_g_builder.componment.adapter.XxjlAdapter;
import structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * XxjlAdapter抽象执行者
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public abstract class XxjlAdapterBuilder {

    private XxjlAdapter xxjlAdapter = new XxjlAdapter();

    /**
     * 执行发件人Dao保存/删除Xxjl行为
     *
     * @param xxjl
     */
    public abstract XxjlAdapterBuilder buildXxjlFjrDao(Xxjl xxjl);

    /**
     * 执行收件人Dao保存/删除Xxjl行为
     *
     * @param xxjl
     */
    public abstract XxjlAdapterBuilder buildXxjlSjrDao(Xxjl xxjl);
}
