package org.bluebridge.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;

/**
 * Dom4j 解析 xml 工具类
 *
 * @author lingwh
 * @date 2019/3/8 19:02
 */
public class Dom4jUtils {

    // 返回 document
    public static Document getDocument(String path) {
        try {
            // 创建解析器
            SAXReader reader = new SAXReader();
            // 得到 document
            Document document = reader.read(path);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 回写 xml 的方法
    public static void xmlWriters(String path, Document document) {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
