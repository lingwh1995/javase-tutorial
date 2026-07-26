package org.bluebridge.structure.proxy.dynamicproxy.dynamicproxy_d;

/**
 * 客户端 - 测试动态代理
 *
 * java8 以上环境运行时需要添加 VM 参数，否则会报错 --add-opens java.base/java.lang=ALL-UNNAMED
 *
 * @author lingwh
 * @date 2019/8/19 11:52
 */
public class Client {

    public static void main(String[] args) {
        // 创建被代理对象
        TeacherDao teacherDao = new TeacherDao();
        // 创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory(teacherDao);
        TeacherDao proxyInstance = (TeacherDao) proxyFactory.getProxyInstance();

        // 调用无返回值的方法
        proxyInstance.teach();

        // 调用有返回值的方法
        String result = proxyInstance.sayello("zhangsan");
        System.out.println("返回值:" + result);
    }
}
