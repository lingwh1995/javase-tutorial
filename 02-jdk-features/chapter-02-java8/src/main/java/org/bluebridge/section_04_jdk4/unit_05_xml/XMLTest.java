package org.bluebridge.section_04_jdk4.unit_05_xml;

import org.junit.Test;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * JDK 1.4 引入的 XML 处理测试
 * 使用 javax.xml.parsers 包解析 XML
 * 包含 DOM 和 SAX 两种解析方式
 *
 * @author lingwh
 * @date 2026/08/05 19:03
 */
public class XMLTest {

    /**
     * 测试使用 DocumentBuilderFactory 解析 XML（DOM 方式）
     * DOM 解析将整个 XML 文档加载到内存中，形成树形结构
     */
    @Test
    public void testDOMParse() throws Exception {
        // 准备 XML 字符串
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<books>"
                + "  <book id=\"1\">"
                + "    <title>Java 核心技术</title>"
                + "    <author>张三</author>"
                + "    <price>99.00</price>"
                + "  </book>"
                + "  <book id=\"2\">"
                + "    <title>深入理解 JVM</title>"
                + "    <author>李四</author>"
                + "    <price>128.00</price>"
                + "  </book>"
                + "</books>";

        // 创建 DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 创建 DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // 解析 XML
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        Document document = builder.parse(inputStream);

        // 获取根元素
        Element root = document.getDocumentElement();
        System.out.println("根元素：" + root.getNodeName());

        // 遍历子元素
        NodeList bookList = root.getElementsByTagName("book");
        System.out.println("共有 " + bookList.getLength() + " 本书：");

        for (int i = 0; i < bookList.getLength(); i++) {
            Element book = (Element) bookList.item(i);
            String id = book.getAttribute("id");
            String title = book.getElementsByTagName("title").item(0).getTextContent();
            String author = book.getElementsByTagName("author").item(0).getTextContent();
            String price = book.getElementsByTagName("price").item(0).getTextContent();

            System.out.println("  书籍 ID: " + id);
            System.out.println("  书名: " + title);
            System.out.println("  作者: " + author);
            System.out.println("  价格: " + price);
            System.out.println("  ---");
        }
    }

    /**
     * 测试使用 SAXParser 解析 XML（SAX 方式）
     * SAX 解析是基于事件驱动的，逐行读取 XML，不会将整个文档加载到内存
     */
    @Test
    public void testSAXParse() throws Exception {
        // 准备 XML 字符串
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<employees>"
                + "  <employee id=\"E001\">"
                + "    <name>王五</name>"
                + "    <department>技术部</department>"
                + "    <salary>15000</salary>"
                + "  </employee>"
                + "  <employee id=\"E002\">"
                + "    <name>赵六</name>"
                + "    <department>市场部</department>"
                + "    <salary>12000</salary>"
                + "  </employee>"
                + "</employees>";

        // 创建 SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 创建 SAXParser
        SAXParser saxParser = factory.newSAXParser();

        // 创建自定义的 Handler
        EmployeeHandler handler = new EmployeeHandler();

        // 解析 XML
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        saxParser.parse(inputStream, handler);
    }

    /**
     * SAX 解析使用的自定义 Handler
     * 继承 DefaultHandler，重写事件处理方法
     */
    private static class EmployeeHandler extends DefaultHandler {
        private StringBuilder currentValue = new StringBuilder();
        private String currentElement;
        private boolean inEmployee = false;

        @Override
        public void startDocument() {
            System.out.println("=== SAX 解析开始 ===");
        }

        @Override
        public void endDocument() {
            System.out.println("=== SAX 解析结束 ===");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            currentElement = qName;
            currentValue.setLength(0);

            if ("employee".equals(qName)) {
                inEmployee = true;
                String id = attributes.getValue("id");
                System.out.println("开始解析员工 ID: " + id);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if ("employee".equals(qName)) {
                inEmployee = false;
                System.out.println("员工解析完成");
                System.out.println("---");
            } else if (inEmployee) {
                System.out.println("  " + qName + ": " + currentValue.toString().trim());
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            currentValue.append(ch, start, length);
        }
    }

    /**
     * 测试 DOM 解析的节点遍历
     * 递归遍历整个 XML 文档树
     */
    @Test
    public void testDOMNodeTraversal() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<library>"
                + "  <name>技术图书馆</name>"
                + "  <books>"
                + "    <book category=\"编程\">Java 编程思想</book>"
                + "    <book category=\"编程\">设计模式</book>"
                + "  </books>"
                + "</library>";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        Document document = builder.parse(inputStream);

        System.out.println("递归遍历 DOM 树：");
        printNode(document.getDocumentElement(), 0);
    }

    /**
     * 递归打印 DOM 节点
     */
    private void printNode(Node node, int indent) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            prefix.append("  ");
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            System.out.println(prefix + "<" + element.getTagName() + ">");

            // 打印属性
            if (element.hasAttributes()) {
                NamedNodeMap attributes = element.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attr = attributes.item(i);
                    System.out.println(prefix + "  [属性] " + attr.getNodeName() + "=" + attr.getNodeValue());
                }
            }

            // 递归遍历子节点
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                printNode(children.item(i), indent + 1);
            }
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String text = node.getTextContent().trim();
            if (!text.isEmpty()) {
                System.out.println(prefix + "  \"" + text + "\"");
            }
        }
    }

    /**
     * 测试创建 XML 文档（使用 DOM API）
     */
    @Test
    public void testCreateXML() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // 创建新文档
        Document document = builder.newDocument();

        // 创建根元素
        Element root = document.createElement("students");
        document.appendChild(root);

        // 创建子元素
        Element student = document.createElement("student");
        student.setAttribute("id", "S001");
        root.appendChild(student);

        // 创建 name 元素
        Element name = document.createElement("name");
        name.setTextContent("小明");
        student.appendChild(name);

        // 创建 age 元素
        Element age = document.createElement("age");
        age.setTextContent("18");
        student.appendChild(age);

        // 创建第二个学生
        Element student2 = document.createElement("student");
        student2.setAttribute("id", "S002");
        root.appendChild(student2);

        Element name2 = document.createElement("name");
        name2.setTextContent("小红");
        student2.appendChild(name2);

        Element age2 = document.createElement("age");
        age2.setTextContent("19");
        student2.appendChild(age2);

        System.out.println("创建的 XML 文档结构：");
        printNode(document.getDocumentElement(), 0);
    }
}