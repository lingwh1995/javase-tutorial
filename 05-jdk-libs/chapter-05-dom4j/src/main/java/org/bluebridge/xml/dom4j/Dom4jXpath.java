package org.bluebridge.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 使用 dom4j 支持 Xpath 表达式直接获取某一个元素
 *
 * @author lingwh
 * @date 2019/3/8 19:02
 */
public class Dom4jXpath {

    /**
     * 使用步骤
     *
     * 1. 引入 jar 包，jaxen-1.1-beta-6.jar
     * 2. 调用相关方法
     * - 获取多个节点：selectNodes("xpath 表达式")
     * - 获取一个节点：selectSingleNode("xpath 表达式")
     */

    private static final String FILE_RELATIVE_PATH = "/dom4j/person.xml";

    public static void main(String[] args) throws DocumentException {
        // 得到 xml 文档的 root 节点
        Document document = dom4jParseXmlgetDocumentObject();

        // 使用 XPATH 得到 xml 中所有的 name 元素的值
        selectAllNameTags(document);
        // 获取第一个 p1 节点下的 name 的值
        selectFirstP1NodeName(document);
    }

    /**
     * 获取第一个 p1 节点下的 name 的值
     *
     * @param @param document 参数
     * @return void 返回类型
     * @throws
     */
    private static void selectFirstP1NodeName(Document document) {
        // 1. 得到 document
        // 2. 获取第一个 p1 节点下的 name 的值
        Node nameNode = document.selectSingleNode("//p1[@id='firstP1']/name");
        System.out.println(nameNode.getText());
        System.out.println(nameNode.getNodeTypeName());
    }

    /**
     * 使用 XPATH 得到 xml 中所有的 name 元素的值
     *
     * @param document 参数
     * @return void 返回类型
     * @throws
     */
    private static void selectAllNameTags(Document document) {
        // 1. 得到 document

        // 2. 使用 selectNodes()得到所有的 name，注意：//p1，p1 是父标签，打印为空
        List<Element> nameNodeList = document.selectNodes("//name");
        // 3. 便利集合
        for (Element nameNode : nameNodeList) {
            System.out.println(nameNode.getText());
        }
    }

    /**
     * 得到 xml 文档的 root 节点
     *
     * @return Element 返回类型
     * @throws
     */
    private static Document dom4jParseXmlgetDocumentObject() throws DocumentException {
        // 1. 创建解析器
        SAXReader saxReader = new SAXReader();
        // 2. 得到 document
        return saxReader.read(getResourceAsStream(FILE_RELATIVE_PATH));
    }

    public static InputStream getResourceAsStream(String xmlName) {
        return Dom4jParseXml.class.getResourceAsStream(xmlName);
    }
}
