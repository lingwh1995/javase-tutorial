package structure.adapter.adapter_g_builder.componment.builder;

import structure.adapter.adapter_g_builder.componment.adapter.XxjlAdapter;
import structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * @author lingwh
 * @desc Xxjl指挥者
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
