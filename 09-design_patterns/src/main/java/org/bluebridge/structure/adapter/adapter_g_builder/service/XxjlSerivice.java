package org.bluebridge.structure.adapter.adapter_g_builder.service;

import org.bluebridge.structure.adapter.adapter_g_builder.componment.builder.XxjlAdapterBuilder;
import org.bluebridge.structure.adapter.adapter_g_builder.componment.builder.XxjlAdapterSaveBuilder;
import org.bluebridge.structure.adapter.adapter_g_builder.componment.builder.XxjlDirector;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * 信息交流服务实现
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlSerivice implements IXxjlService {

    /**
     * 保存行为构建者
     */
    private XxjlAdapterBuilder xxjlAdapterSaveBuilder = new XxjlAdapterSaveBuilder();

    /**
     * 删除行为构建者
     */
    private XxjlAdapterBuilder xxjlAdapterDeleteBuilder = new XxjlAdapterSaveBuilder();

    /**
     * 指挥者
     */
    private XxjlDirector xxjlDirector = new XxjlDirector();

    @Override
    public void save(Xxjl xxjl) throws Exception {
        // 设置实际构建者
        xxjlDirector.setXxjlAdapter(xxjlAdapterSaveBuilder);
        xxjlDirector.save(xxjl, "123", "123@qq.com");
    }

    @Override
    public void delete(Xxjl xxjl) throws Exception {
        // 重置实际构建者
        xxjlDirector.setXxjlAdapter(xxjlAdapterDeleteBuilder);
        xxjlDirector.save(xxjl, "123", "123@qq.com");
    }
}
