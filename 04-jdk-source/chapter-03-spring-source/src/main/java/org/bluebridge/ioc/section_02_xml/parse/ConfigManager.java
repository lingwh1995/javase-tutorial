package org.bluebridge.ioc.section_02_xml.parse;

import org.apache.commons.lang3.StringUtils;
import org.bluebridge.ioc.section_02_xml.config.Bean;
import org.bluebridge.ioc.section_02_xml.config.Properties;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 解析 xml，返回读取结果
 *
 * @author lingwh
 * @date 2023/12/7 10:15
 */
public class ConfigManager {

    /**
     * 日志记录对象
     */
    private static Logger logger = Logger.getLogger("ConfigManager.class");

    @SuppressWarnings("unchecked")
    public static Map<String, Bean> parseXml(String path) {
        // 1. 获取解析器
        SAXReader reader = new SAXReader();
        // 2. 获取根节点
        Document document = null;
        try {
            document = reader.read(ConfigManager.class.getClassLoader().getResource(path));
        } catch (DocumentException e) {
            e.printStackTrace();
            logger.info("读取xml文件发生了异常，请检查文件名称和路径是否正确？");
        }
        // 3. 解析 xml，找出所有的<bean/>标签
        Element rootElement = document.getRootElement();
        List<Element> beanElements = rootElement.elements("bean");
        Map<String, Bean> map = new HashMap<String, Bean>();
        if (!beanElements.isEmpty()) {
            for (Element beanElement : beanElements) {
                // 4. 把每一个 bean 标签的 id/class 封装到 Bean 这个实体中
                Bean bean = new Bean();
                bean.setId(beanElement.attributeValue("id"));
                bean.setClassName(beanElement.attributeValue("class"));
                String scope = beanElement.attributeValue("scope");
                if (StringUtils.isNotBlank(scope)) {
                    bean.setScope(scope);
                }
                // 5. 遍历每一个 Bean 标签下面的<property/>，并把 name/value/value 封装到 Properties 这个实体中
                List<Element> propertyElements = beanElement.elements("property");
                // 存放每一个 Bean 节点下面的<property/>
                List<Properties> listProperties = new ArrayList<Properties>();
                if (!propertyElements.isEmpty()) {
                    for (Element propertyElement : propertyElements) {
                        Properties properties = new Properties();
                        properties.setName(propertyElement.attributeValue("name"));
                        properties.setRef(propertyElement.attributeValue("ref"));
                        properties.setValue(propertyElement.attributeValue("value"));
                        listProperties.add(properties);
                    }
                }
                bean.setPerproties(listProperties);
                logger.info(bean.toString());
                map.put(beanElement.attributeValue("id"), bean);
            }
        }
        return map;
    }
}
