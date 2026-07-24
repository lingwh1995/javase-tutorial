package org.bluebridge.ioc.section_01_xml;

import java.util.HashMap;

/**
 * 描述 class/beanId 和其子属性的对应关系
 *
 * @author lingwh
 * @date 2019/3/14 19:02
 */
public class BeanMatch {

    private String classValue;
    private HashMap<String, String> classMatchChildAttributeAndAttributeValueMap;

    public BeanMatch() {
    }

    public BeanMatch(String classValue, HashMap<String, String> classMatchChildAttributeAndAttributeValueMap) {
        this.classValue = classValue;
        this.classMatchChildAttributeAndAttributeValueMap = classMatchChildAttributeAndAttributeValueMap;
    }

    public String getClassValue() {
        return classValue;
    }

    public void setClassValue(String classValue) {
        this.classValue = classValue;
    }

    public HashMap<String, String> getClassMatchChildAttributeAndAttributeValueMap() {
        return classMatchChildAttributeAndAttributeValueMap;
    }

    public void setClassMatchChildAttributeAndAttributeValueMap(
            HashMap<String, String> classMatchChildAttributeAndAttributeValueMap) {
        this.classMatchChildAttributeAndAttributeValueMap = classMatchChildAttributeAndAttributeValueMap;
    }

    @Override
    public String toString() {
        return "BeanMatch{" +
                "classValue='" + classValue + '\'' +
                ", classMatchChildAttributeAndAttributeValueMap=" + classMatchChildAttributeAndAttributeValueMap +
                '}';
    }
}
