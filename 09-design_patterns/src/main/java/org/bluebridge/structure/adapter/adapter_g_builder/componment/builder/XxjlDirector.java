package org.bluebridge.structure.adapter.adapter_g_builder.componment.builder;

import org.bluebridge.structure.adapter.adapter_g_builder.componment.adapter.XxjlAdapter;
import org.bluebridge.structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * Xxjl指挥者
 *
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlDirector {

    private XxjlAdapterBuilder xxjlAdapter;
    private XxjlAdapterBaseBuilder xxjlAdapterBaseConcerteBuilder = new XxjlAdapterBaseConcerteBuilder();

    public XxjlDirector() {

    }

    public XxjlDirector(XxjlAdapter xxjlAdapter) {
        this.xxjlAdapter = new XxjlAdapterSaveBuilder();
    }

    public void setXxjlAdapter(XxjlAdapterBuilder xxjlAdapter) {
        this.xxjlAdapter = xxjlAdapter;
    }

    public void save(Xxjl xxjl, String phoneNumber, String email) {
        xxjlAdapter.buildXxjlSjrDao(xxjl).buildXxjlFjrDao(xxjl);
        xxjlAdapterBaseConcerteBuilder.build(xxjl, phoneNumber, email);
    }

    public void delete(Xxjl xxjl, String phoneNumber, String email) {
        xxjlAdapter.buildXxjlSjrDao(xxjl).buildXxjlFjrDao(xxjl);
        xxjlAdapterBaseConcerteBuilder.build(xxjl, phoneNumber, email);
    }
}
