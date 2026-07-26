package org.bluebridge.action.interpreter.interpreter_c;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 读取配置文件
 *
 * @author lingwh
 * @date 2019/8/27 13:42
 */
public class ReadAppXml {

    /**
     * 读取配置文件内容
     *
     * @param filePathName 配置文件的路径和文件名
     * @throws Exception
     */
    public void read(String filePathName) throws Exception {
        Document doc = null;
        // 建立一个解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 获得一个 DocumentBuilder 对象，这个对象代表了具体的 DOM 解析器
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 得到一个表示 XML 文档的 Document 对象
        doc = builder.parse(filePathName);
        // 去掉 XML 中作为格式化内容的空白而映射在 DOM 树中的 Text Node 对象
        doc.normalize();

        // 获取 jdbc 的配置值
        NodeList jdbc = doc.getElementsByTagName("jdbc");
        // 只有一个 jdbc，获取 jdbc 中的驱动类的名称
        NodeList driverClassNode = ((Element) jdbc.item(0)).getElementsByTagName("driver-class");
        String driverClass = driverClassNode.item(0).getFirstChild().getNodeValue();
        System.out.println("driverClass==" + driverClass);

        // 同理获取 url、user、password 等的值
        NodeList urlNode = ((Element) jdbc.item(0)).getElementsByTagName("url");
        String url = urlNode.item(0).getFirstChild().getNodeValue();
        System.out.println("url==" + url);

        NodeList userNode = ((Element) jdbc.item(0)).getElementsByTagName("user");
        String user = userNode.item(0).getFirstChild().getNodeValue();
        System.out.println("user==" + user);

        NodeList passwordNode = ((Element) jdbc.item(0)).getElementsByTagName("password");
        String password = passwordNode.item(0).getFirstChild().getNodeValue();
        System.out.println("password==" + password);

        // 获取 application-xml
        NodeList applicationXmlNode = doc.getElementsByTagName("application-xml");
        String applicationXml = applicationXmlNode.item(0).getFirstChild().getNodeValue();
        System.out.println("applicationXml==" + applicationXml);
    }
}
