package org.bluebridge.ioc.section_02_xml.config;

import java.util.List;

/**
 * Bean 标签配置
 *
 * @author lingwh
 * @date 2019/3/16 19:02
 */
public class Bean {

    private String id;
    private String className;
    private String scope = "single_thread";
    private List<Properties> perproties;

    /**
     * 创建一个新的实例 Bean
     */
    public Bean() {
        super();
    }

    /**
     * 创建一个新的实例 Bean
     *
     * @param id
     * @param className
     * @param scope
     * @param perproties
     */
    public Bean(String id, String className, String scope, List<Properties> perproties) {
        super();
        this.id = id;
        this.className = className;
        this.scope = scope;
        this.perproties = perproties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Properties> getPerproties() {
        return perproties;
    }

    public void setPerproties(List<Properties> perproties) {
        this.perproties = perproties;
    }

    @Override
    public String toString() {
        return "Bean [id=" + id + ", className=" + className + ", scope=" + scope + ", perproties=" + perproties + "]";
    }
}
