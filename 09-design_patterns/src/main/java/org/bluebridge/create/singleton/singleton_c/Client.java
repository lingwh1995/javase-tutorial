package org.bluebridge.create.singleton.singleton_c;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/8/5 16:49
 */
public class Client {

    public static void main(String[] args) {
        // 获取单例的PropertiesUtils
        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
        PropertiesUtils propertiesUtils1 = PropertiesUtils.getInstance();
        System.out.println(propertiesUtils == propertiesUtils1);
        propertiesUtils.read();
    }
}
