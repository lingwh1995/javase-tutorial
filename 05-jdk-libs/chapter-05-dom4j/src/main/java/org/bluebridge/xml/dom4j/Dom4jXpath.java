package org.bluebridge.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 使用dom4j支持Xpath表达式直接获取某一个元素
 *
 * @author lingwh
 * @date 2019/3/8 19:02
 */
public class Dom4jXpath {

    /**
     * 使用步骤
     *
     * 1. 引入jar包,jaxen-1.1-beta-6.jar
     * 2. 调用相关方法
     * - 获取多个节点:selectNodes("xpath表达式")
     * - 获取一个节点:selectSingleNode("xpath表达式")
     */

    private static final String FILE_RELATIVE_PATH = "/dom4j/person.xml";

    public static void main(String[] args) throws DocumentException {
        // 得到xml文档的root节点
        Document document = dom4jParseXmlgetDocumentObject();

        // 使用XPATH得到xml中所有的name元素的值
        selectAllNameTags(document);
        // 获取第一个p1节点下的name的值
        selectFirstP1NodeName(document);
    }

    /**
     * 获取第一个p1节点下的name的值
     *
     * @param @param document 参数
     * @return void 返回类型
     * @throws
     */
    private static void selectFirstP1NodeName(Document document) {
        // 1. 得到document
        // 2. 获取第一个p1节点下的name的值
        Node nameNode = document.selectSingleNode("//p1[@id='firstP1']/name");
        System.out.println(nameNode.getText());
        System.out.println(nameNode.getNodeTypeName());
    }

    /**
     * 使用XPATH得到xml中所有的name元素的值
     *
     * @param document 参数
     * @return void 返回类型
     * @throws
     */
    private static void selectAllNameTags(Document document) {
        // 1. 得到document

        // 2. 使用selectNodes()得到所有的name,注意://p1,p1是父标签,打印为空
        List<Element> nameNodeList = document.selectNodes("//name");
        // 3. 便利集合
        for (Element nameNode : nameNodeList) {
            System.out.println(nameNode.getText());
        }
    }

    /**
     * 得到xml文档的root节点
     *
     * @return Element 返回类型
     * @throws
     */
    private static Document dom4jParseXmlgetDocumentObject() throws DocumentException {
        // 1. 创建解析器
        SAXReader saxReader = new SAXReader();
        // 2. 得到document
        return saxReader.read(getResourceAsStream(FILE_RELATIVE_PATH));
    }

    public static InputStream getResourceAsStream(String xmlName) {
        return Dom4jParseXml.class.getResourceAsStream(xmlName);
    }
}
