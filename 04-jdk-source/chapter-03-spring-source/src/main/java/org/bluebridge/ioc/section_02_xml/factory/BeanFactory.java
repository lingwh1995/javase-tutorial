package org.bluebridge.ioc.section_02_xml.factory;

/**
 * Bean 工厂
 *
 * @author lingwh
 * @date 2019/3/15 19:02
 */
public interface BeanFactory {

    /**
     * 获取 bean
     *
     * @param beanId
     * @return
     */
    Object getBean(String beanId);

    /**
     * 获取 bean
     *
     * @param beanId
     * @param t
     * @param <T>
     * @return
     */
    <T> T getBean(String beanId, Class<T> t);
}
