package org.bluebridge.section_03_jdk3.unit_01_proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 1.3 动态代理(Dynamic Proxy)特性测试
 *
 * JDK 1.3 在 java.lang.reflect 包中引入了动态代理机制, 允许在运行时动态创建
 * 实现了指定接口的代理类。这是 AOP(Aspect Oriented Programming) 的基础支持。
 *
 * 核心类:
 * 1. java.lang.reflect.Proxy: 提供静态方法创建代理类和代理实例
 * 2. java.lang.reflect.InvocationHandler: 调用处理器接口, 代理实例的方法调用
 *    会被分发到该接口的 invoke 方法上
 *
 * 与静态代理的对比:
 * - 静态代理: 需要手动编写代理类, 每个目标类都需要一个代理类, 代码冗余
 * - 动态代理: 运行时动态生成代理类, 一个 InvocationHandler 可代理多个目标类
 *
 * @author lingwh
 * @date 2026/08/05 19:06
 */
public class DynamicProxyTest {

    // ==================== 业务接口与实现 ====================

    /**
     * 业务接口: 用户服务
     */
    interface UserService {
        /**
         * 保存用户
         */
        void saveUser(String username);

        /**
         * 查询用户
         */
        String findUser(Integer id);
    }

    /**
     * 业务接口: 订单服务
     */
    interface OrderService {
        /**
         * 创建订单
         */
        void createOrder(String orderNo);
    }

    /**
     * UserService 的真实实现类
     */
    static class UserServiceImpl implements UserService {
        @Override
        public void saveUser(String username) {
            System.out.println("[真实业务] 保存用户: " + username);
        }

        @Override
        public String findUser(Integer id) {
            System.out.println("[真实业务] 查询用户, id: " + id);
            return "用户_" + id;
        }
    }

    /**
     * OrderService 的真实实现类
     */
    static class OrderServiceImpl implements OrderService {
        @Override
        public void createOrder(String orderNo) {
            System.out.println("[真实业务] 创建订单: " + orderNo);
        }
    }

    // ==================== 静态代理实现 ====================

    /**
     * UserService 的静态代理类
     *
     * 缺点: 每个接口都需要编写对应的代理类, 代码冗余;
     * 且只能代理单一接口, 无法复用代理逻辑。
     */
    static class UserServiceStaticProxy implements UserService {
        // 持有目标对象的引用
        private final UserService target;

        public UserServiceStaticProxy(UserService target) {
            this.target = target;
        }

        @Override
        public void saveUser(String username) {
            System.out.println("[静态代理] 前置处理: 开启事务");
            try {
                target.saveUser(username);
                System.out.println("[静态代理] 后置处理: 提交事务");
            } catch (Exception e) {
                System.out.println("[静态代理] 异常处理: 回滚事务");
                throw e;
            }
        }

        @Override
        public String findUser(Integer id) {
            System.out.println("[静态代理] 前置处理: 开启事务");
            try {
                String result = target.findUser(id);
                System.out.println("[静态代理] 后置处理: 提交事务");
                return result;
            } catch (Exception e) {
                System.out.println("[静态代理] 异常处理: 回滚事务");
                throw e;
            }
        }
    }

    // ==================== 动态代理实现 ====================

    /**
     * 通用 InvocationHandler 实现: 日志记录 + 事务管理
     *
     * 同一个 InvocationHandler 可以代理任意接口, 实现了代理逻辑的复用。
     */
    static class LogInvocationHandler implements InvocationHandler {
        // 持有目标对象
        private final Object target;

        public LogInvocationHandler(Object target) {
            this.target = target;
        }

        /**
         * 代理方法调用
         *
         * @param proxy  代理对象
         * @param method 被调用的方法
         * @param args   方法参数
         * @return 方法调用结果
         * @throws Throwable 可能抛出的异常
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("[动态代理] 前置处理: 记录日志, 方法=" + method.getName());
            System.out.println("[动态代理] 前置处理: 开启事务");

            Object result = null;
            try {
                // 调用目标对象的真实方法
                result = method.invoke(target, args);
                System.out.println("[动态代理] 后置处理: 提交事务");
            } catch (Exception e) {
                System.out.println("[动态代理] 异常处理: 回滚事务, 异常=" + e.getCause().getMessage());
                throw e.getCause();
            } finally {
                System.out.println("[动态代理] 最终处理: 释放资源");
            }

            return result;
        }
    }

    // ==================== 测试方法 ====================

    /**
     * 测试使用 Proxy.newProxyInstance() 创建动态代理对象
     *
     * 动态代理的核心 API:
     * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
     * - loader: 类加载器, 通常使用目标类的类加载器
     * - interfaces: 代理类需要实现的接口列表
     * - h: 调用处理器, 代理对象的方法调用会转发给该处理器
     */
    @Test
    public void testDynamicProxyBasic() {
        System.out.println("========== 动态代理基本使用 ==========");

        // 1. 创建目标对象
        UserService userService = new UserServiceImpl();

        // 2. 创建 InvocationHandler
        LogInvocationHandler handler = new LogInvocationHandler(userService);

        // 3. 使用 Proxy.newProxyInstance() 创建动态代理对象
        UserService proxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),  // 类加载器
                userService.getClass().getInterfaces(),   // 要代理的接口
                handler                                   // 调用处理器
        );

        // 4. 通过代理对象调用方法
        System.out.println("--- 调用 saveUser 方法 ---");
        proxy.saveUser("张三");

        System.out.println();
        System.out.println("--- 调用 findUser 方法 ---");
        String user = proxy.findUser(1001);
        System.out.println("查询结果: " + user);
    }

    /**
     * 测试动态代理的代理对象信息
     *
     * 动态代理生成的代理类具有以下特征:
     * - 类名格式: $Proxy + 数字序号
     * - 代理类继承了 java.lang.reflect.Proxy
     * - 代理类实现了指定的接口
     */
    @Test
    public void testDynamicProxyObjectInfo() {
        System.out.println("========== 动态代理对象信息 ==========");

        UserService userService = new UserServiceImpl();
        LogInvocationHandler handler = new LogInvocationHandler(userService);

        UserService proxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler
        );

        // 查看代理对象的类名
        System.out.println("代理对象类名: " + proxy.getClass().getName());

        // 查看代理对象的父类
        System.out.println("代理对象父类: " + proxy.getClass().getSuperclass().getName());

        // 查看代理对象实现的接口
        Class<?>[] interfaces = proxy.getClass().getInterfaces();
        System.out.println("代理对象实现的接口: ");
        for (Class<?> intf : interfaces) {
            System.out.println("  " + intf.getName());
        }

        // 判断代理对象是否为 Proxy 类型
        System.out.println("是否是 Proxy 实例: " + (proxy instanceof Proxy));
    }

    /**
     * 测试同一个 InvocationHandler 代理多个不同接口
     *
     * 动态代理的优势: 一个 InvocationHandler 可以复用于多个不同的业务接口,
     * 实现横切关注点(如日志、事务、权限检查)的统一处理。
     */
    @Test
    public void testDynamicProxyMultipleInterfaces() {
        System.out.println("========== 同一个 Handler 代理多个接口 ==========");

        // 代理 UserService
        UserService userService = new UserServiceImpl();
        LogInvocationHandler handler = new LogInvocationHandler(userService);
        UserService userProxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler
        );
        System.out.println("--- 通过代理调用 UserService ---");
        userProxy.saveUser("李四");

        System.out.println();

        // 代理 OrderService
        OrderService orderService = new OrderServiceImpl();
        // 复用同一个 LogInvocationHandler
        LogInvocationHandler orderHandler = new LogInvocationHandler(orderService);
        OrderService orderProxy = (OrderService) Proxy.newProxyInstance(
                orderService.getClass().getClassLoader(),
                orderService.getClass().getInterfaces(),
                orderHandler
        );
        System.out.println("--- 通过代理调用 OrderService ---");
        orderProxy.createOrder("ORDER_20251202001");
    }

    /**
     * 对比静态代理与动态代理
     *
     * 通过对比可以看出动态代理相比静态代理的核心优势:
     * 1. 减少代码冗余: 不需要为每个接口编写代理类
     * 2. 提高复用性: 同一个 InvocationHandler 可代理多个接口
     * 3. 更好的维护性: 横切逻辑集中管理, 修改一处即生效
     */
    @Test
    public void testStaticVsDynamicProxy() {
        System.out.println("========== 静态代理 vs 动态代理 ==========");

        // ===== 静态代理 =====
        System.out.println("--- 静态代理 ---");
        UserService userService = new UserServiceImpl();
        UserService staticProxy = new UserServiceStaticProxy(userService);
        staticProxy.saveUser("王五");

        // 如果需要代理 OrderService, 必须再编写一个 OrderServiceStaticProxy 类
        // 每个接口都需要一个对应的静态代理类, 代码量会随着接口数量线性增长

        System.out.println();

        // ===== 动态代理 =====
        System.out.println("--- 动态代理 ---");
        // 使用同一个 InvocationHandler 即可代理任意接口
        LogInvocationHandler handler = new LogInvocationHandler(userService);
        UserService dynamicProxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler
        );
        dynamicProxy.saveUser("赵六");

        System.out.println();
        System.out.println("静态代理缺点: 每个接口都需要编写一个代理类, 代码冗余, 维护困难");
        System.out.println("动态代理优点: 运行时动态生成代理类, 一个 InvocationHandler 可代理多个接口");
    }

    /**
     * 测试动态代理的 equals/hashCode/toString 方法
     *
     * 注意: 通过 Proxy.newProxyInstance 创建的代理对象, 其 equals、hashCode、
     * toString 方法也会被转发到 InvocationHandler 的 invoke 方法。
     * 但调用代理对象的 getClass() 方法不会被转发, 因为它是在 Proxy 类中定义的 final 方法。
     */
    @Test
    public void testDynamicProxyObjectMethods() {
        System.out.println("========== 动态代理对象方法测试 ==========");

        UserService userService = new UserServiceImpl();
        LogInvocationHandler handler = new LogInvocationHandler(userService);

        UserService proxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler
        );

        // 调用 toString() 方法, 会被转发到 InvocationHandler
        System.out.println("调用 toString(): " + proxy.toString());

        // 调用 hashCode() 方法, 会被转发到 InvocationHandler
        System.out.println("调用 hashCode(): " + proxy.hashCode());

        // 判断代理对象是否实现了指定接口
        System.out.println("代理对象是否实现 UserService: " + (proxy instanceof UserService));
        System.out.println("代理对象是否实现 Proxy 子类: " + (proxy instanceof Proxy));
    }
}