package org.bluebridge.ioc.xml_two.factory;

/**
 * @author lingwh
 * @desc Bean工厂
 * @date 2019/3/15 00:00
 */
public interface BeanFactory {

    /**
     * 获取bean
     *
     * @param beanId
     * @return
     */
    Object getBean(String beanId);

    /**
     * 获取bean
     *
     * @param beanId
     * @param t
     * @param <T>
     * @return
     */
    <T> T getBean(String beanId, Class<T> t);
}
