package org.bluebridge.ioc.section_03_anno.parse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.logging.Logger;

/**
 * 获取 base-package 配置的路径
 *
 * @author lingwh
 * @date 2026/4/21 10:30
 */
public class ConfigManagerAnno {

    private static Logger logger = Logger.getLogger("ConfigManagerAnno.class");

    public static String getXmlConfig(String path) {
        InputStream inputStream = ConfigManagerAnno.class.getClassLoader().getResourceAsStream(path);
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
            logger.info("解析xml失败，请检查xml文件名是否正确!");
        }
        Element rootElement = document.getRootElement();
        rootElement.addNamespace("context", "http://www.springframework.org/schema/context");
        Element element = rootElement.element("component-scan");
        String basePackage = null;
        if (element != null) {
            basePackage = element.attributeValue("base-package");
        }
        return basePackage;
    }
}
