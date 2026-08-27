package org.bluebridge.action.interpreter.interpreter_d;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * XML 工具类
 *
 * @author lingwh
 * @date 2019/8/27 14:08
 */
public class XmlUtil {

    public static Document getRoot(String filePathName) throws Exception {
        Document doc = null;
        // 建立一个解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 获得一个 DocumentBuilder 对象，这个对象代表了具体的 DOM 解析器
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 得到一个表示 XML 文档的 Document 对象
        doc = builder.parse(filePathName);
        // 去掉 XML 文档中作为格式化内容的空白而映射在 DOM 树中的 TextNode 对象
        doc.normalize();
        return doc;
    }
}
