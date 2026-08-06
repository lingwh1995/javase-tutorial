package org.bluebridge.section_01_jdk1.unit_05_rmi;

import org.junit.Test;

import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

/**
 * JDK 1.1 RMI(Remote Method Invocation)远程方法调用测试
 *
 * JDK 1.1 引入 java.rmi 包, 支持 Java 对象之间的远程方法调用, 核心组件如下:
 * 1. Remote 接口: 远程接口的标记接口, 远程接口必须继承它, 方法必须声明抛出 RemoteException
 * 2. UnicastRemoteObject: 远程对象基类, 继承它可自动完成远程对象的导出和网络通信
 * 3. Registry 注册表: 保存远程对象, 服务端绑定远程对象, 客户端按名字查找
 * RMI 服务端流程: 创建远程对象 -> 导出远程对象 -> 创建 Registry -> 绑定远程对象
 * RMI 客户端流程: 获取 Registry -> 查找远程对象 -> 调用远程方法
 * 注意: 本测试仅展示代码结构和流程, 不会实际启动 RMI 服务或发起远程调用
 *
 * @author lingwh
 * @date 2026/08/05 18:26
 */
public class RmiTest {

    /**
     * 远程接口: 必须继承 java.rmi.Remote, 方法必须声明抛出 RemoteException
     */
    public interface HelloService extends Remote {

        /**
         * 远程方法: 必须声明抛出 RemoteException
         */
        String sayHello(String name) throws RemoteException;
    }

    /**
     * 远程接口实现类: 继承 UnicastRemoteObject 自动完成远程对象导出
     */
    public static class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

        private static final long serialVersionUID = 1L;

        /**
         * 构造方法必须抛出 RemoteException
         */
        protected HelloServiceImpl() throws RemoteException {
            super();
        }

        @Override
        public String sayHello(String name) throws RemoteException {
            return "Hello, " + name;
        }
    }

    /**
     * 测试 RMI 服务端代码结构(演示代码结构, 不实际启动 RMI 服务)
     */
    @Test
    public void testRmiServerStructure() {
        // 实际开发中 RMI 服务端代码如下:
        // 第一步: 创建远程对象
        // HelloService helloService = new HelloServiceImpl();
        // 第二步: 导出远程对象(继承 UnicastRemoteObject 构造时已自动导出, 也可调用 exportObject 手动导出)
        // HelloService stub = (HelloService) UnicastRemoteObject.exportObject(helloService, 0);
        // 第三步: 在本地主机创建注册表, 默认端口 1099
        // Registry registry = LocateRegistry.createRegistry(1099);
        // 第四步: 将远程对象绑定到注册表, 供客户端按名字查找
        // registry.rebind("HelloService", stub);
        System.out.println("RMI 服务端流程: 创建远程对象 -> 导出远程对象 -> 创建 Registry -> 绑定远程对象到注册表");
        System.out.println("说明: 不实际启动 RMI 服务, 上述代码在实际环境中启用");
    }

    /**
     * 测试 RMI 客户端代码结构(演示代码结构, 不实际连接 RMI 服务)
     */
    @Test
    public void testRmiClientStructure() {
        // 实际开发中 RMI 客户端代码如下:
        // 第一步: 获取远程主机的注册表
        // Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        // 第二步: 从注册表中按名字查找远程对象, 返回远程对象的桩(Stub)
        // HelloService helloService = (HelloService) registry.lookup("HelloService");
        // 第三步: 像调用本地方法一样调用远程方法
        // String result = helloService.sayHello("张三");
        System.out.println("RMI 客户端流程: 获取 Registry -> 按名字查找远程对象 -> 调用远程方法");
        System.out.println("说明: 不实际连接 RMI 服务, 上述代码在实际环境中启用");
    }

    /**
     * 测试远程接口的定义规范: 继承 Remote 接口, 方法抛出 RemoteException
     */
    @Test
    public void testRemoteInterfaceDefinition() {
        // 验证 HelloService 是 Remote 接口的子接口
        boolean isRemote = Remote.class.isAssignableFrom(HelloService.class);
        System.out.println("HelloService 是否继承 Remote 接口: " + isRemote);
        // 验证远程接口中的方法是否声明抛出 RemoteException
        Method[] methods = HelloService.class.getDeclaredMethods();
        for (Method method : methods) {
            boolean throwRemoteException = Arrays.asList(method.getExceptionTypes()).contains(RemoteException.class);
            System.out.println("方法: " + method.getName() + ", 是否抛出 RemoteException: " + throwRemoteException);
        }
    }
}
