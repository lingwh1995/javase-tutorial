package org.bluebridge.ioc.xml_two.config;

/**
 * @author lingwh
 * @desc 属性标签配置
 * @date 2019/3/16 00:00
 */
public class Properties {
    private String name;
    private String value;
    private String ref;

    /**
     * 创建一个新的实例 Properties.
     */
    public Properties() {
        super();
    }

    /**
     * 创建一个新的实例 Properties.
     *
     * @param name
     * @param value
     * @param ref
     */
    public Properties(String name, String value, String ref) {
        super();
        this.name = name;
        this.value = value;
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Properties [name=" + name + ", value=" + value + ", ref=" + ref + "]";
    }
}
