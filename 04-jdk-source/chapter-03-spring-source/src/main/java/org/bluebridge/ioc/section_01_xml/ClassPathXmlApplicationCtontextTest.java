package org.bluebridge.ioc.section_01_xml;

import org.dom4j.DocumentException;

/**
 * XML方式应用上下文测试
 *
 * @author lingwh
 * @date 2019/3/13 19:02
 */
public class ClassPathXmlApplicationCtontextTest {

    public static void main(String[] args)
            throws DocumentException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchFieldException, SecurityException {
        ClassPathXmlApplicationCtontext classPathXmlApplicationCtontext = new ClassPathXmlApplicationCtontext(
                "applicationContext-ioc-xml-one.xml");
        User user1 = (User) classPathXmlApplicationCtontext.getBean("user");
        System.out.println("未重载的getBean()方法:" + user1);
        User user2 = classPathXmlApplicationCtontext.getBean("user", User.class);
        System.out.println("重载增强的getBean()方法:" + user2);
    }
}
