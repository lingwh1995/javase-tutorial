package org.bluebridge.structure.adapter.adapter_g_builder.componment.builder;

import org.bluebridge.structure.adapter.adapter_g_builder.componment.adapter.XxjlAdapter;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * XxjlAdapter 抽象执行者
 *
 * @author lingwh
 * @date 2026/7/22 20:21
 */
public abstract class XxjlAdapterBaseBuilder {

    private XxjlAdapter xxjlAdapter = new XxjlAdapter();

    /**
     * 执行 OpenfireDao 保存 Xxjl 行为
     *
     * @param xxjl
     */
    public abstract XxjlAdapterBaseBuilder buildXxjlOpenfireDao(Xxjl xxjl);

    /**
     * 执行公安厅指令 Dao 发送短信行为
     *
     * @param phoneNumber
     */
    public abstract XxjlAdapterBaseBuilder buildGatzlDao(String phoneNumber);

    /**
     * 执行通知通告 Dao 发送邮件行为
     *
     * @param email
     */
    public abstract XxjlAdapterBaseBuilder buildTztgDao(String email);

    /**
     * 执行基础构建
     */
    public void build(Xxjl xxjl, String phoneNumber, String email) {
        buildXxjlOpenfireDao(xxjl)
                .buildTztgDao(phoneNumber)
                .buildTztgDao(email);
    }
}
