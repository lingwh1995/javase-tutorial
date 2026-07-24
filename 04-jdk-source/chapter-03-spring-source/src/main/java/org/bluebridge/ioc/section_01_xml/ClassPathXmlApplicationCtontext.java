package org.bluebridge.ioc.section_01_xml;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 模拟 spring 框架 ClassPathXmlApplicationContext
 *
 * @author lingwh
 * @date 2019/3/13 19:02
 */
public class ClassPathXmlApplicationCtontext {

    /**
     * 记录日志
     */
    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger("ClassPathXmlApplicationCtontext.class");

    /**
     * SpringIOC 示例代码
     *
     * ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
     * UserService userService = (UserService) applicationContext.getBean("userService");
     *
     * IOC 步骤
     *
     * 1. 解析 xml
     * 2. 查找根据<bean/>标签配置的 id 值找到 class 属性的配置的值
     * 3. 使用反射初始化 bean
     */
    private String xmlPath;

    public ClassPathXmlApplicationCtontext(String xmlUrl) {
        this.xmlPath = xmlUrl;
    }

    public Object getBean(String beanId)
            throws DocumentException, ClassNotFoundException, InstantiationException,
                IllegalAccessException, NoSuchFieldException, SecurityException {
        if (StringUtils.isBlank(xmlPath) && StringUtils.isEmpty(xmlPath)) {
            throw new RuntimeException("文件路径为空！");
        }
        /**
         * 1. 获取所有 xml 节点信息
         */
        List<Element> xmlElements = parseXml(xmlPath);
        if (xmlElements.isEmpty()) {
            throw new RuntimeException("该xml文件没有子节点！");
        }
        /**
         * 2. 查找是否有和该 beanId 对应的节点，如果有，返回该节点 class 属性值和该 beanId 对应的节点的字节点的属性名和属性值(即 JavaBean 中属性名和属性值)
         */
        BeanMatch beanMatch = findBeanClassByBeanId(xmlElements, beanId);
        try {
            Set<Entry<String, String>> beanMatchEntrySet =
                    beanMatch.getClassMatchChildAttributeAndAttributeValueMap().entrySet();
            Iterator<Entry<String, String>> beanMatchEntrySetIterator = beanMatchEntrySet.iterator();
            /**
             * 3. 根据该值创建 bean
             */
            Class<?> objectClass = Class.forName(beanMatch.getClassValue());
            Object instance = objectClass.newInstance();
            while (beanMatchEntrySetIterator.hasNext()) {
                Entry<String, String> beanMatchEntry = beanMatchEntrySetIterator.next();
                /**
                 * 4. 给 bean 的属性进行赋值
                 */
                Field beanField = objectClass.getDeclaredField(beanMatchEntry.getKey());
                beanField.setAccessible(true);
                beanField.set(instance, beanMatchEntry.getValue());
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(xmlPath + "中没有与" + beanId + "对应的节点!");
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanId, Class<T> t)
            throws ClassNotFoundException,
                    InstantiationException,
                    IllegalAccessException,
                    NoSuchFieldException,
                    SecurityException,
                    DocumentException {
        System.out.println(t);
        return (T) getBean(beanId);
    }

    /**
     * 查找是否有和该 beanId 对应的节点，如果有，返回该节点 class 属性值
     *
     * @param xmlElements
     * @param beanId
     * @return void
     * @throws
     */
    private BeanMatch findBeanClassByBeanId(List<Element> xmlElements, String beanId) {
        // 1. 遍历 xml 节点，根据 beanId 查找对应的节点
        Iterator<Element> iterator = xmlElements.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            // 2. 查找该节点上是否有对应的 id
            String beanIdValue = element.attributeValue("id");
            if (beanIdValue.equals(beanId)) {
                String attributeClassValue = element.attributeValue("class");
                // 3. 判断带节点下有没有子元素，有元素就是属性值和属性名
                List<Element> childNode = element.elements();
                Iterator<Element> childNodeElements = childNode.iterator();
                // 把属性值和属性名封装到这个 map 中去
                HashMap<String, String> childNodeAttributeMap = new HashMap<String, String>();
                while (childNodeElements.hasNext()) {
                    Element childNodeElement = childNodeElements.next();
                    String childNodeAttributeName = childNodeElement.attributeValue("name");
                    String childNodeAttributeValue = childNodeElement.attributeValue("value");
                    childNodeAttributeMap.put(childNodeAttributeName, childNodeAttributeValue);
                }
                BeanMatch beanMatch = new BeanMatch(attributeClassValue, childNodeAttributeMap);
                return beanMatch;
            }
        }
        return null;
    }

    /**
     * 解析 xml 文件
     *
     * @param xmlPath
     * @throws DocumentException
     * @return List<Element>
     * @throws
     */
    public List<Element> parseXml(String xmlPath) throws DocumentException {
        // 1. 获取资源文件(applicationContext.xml)
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(xmlPath);
        // 2. 解析该 xml 文件，获取所有节点信息
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(resourceAsStream);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        return elements;
    }
}
