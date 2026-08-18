package org.bluebridge.section_03_jdk3.unit_02_jndi;

import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * JDK 1.3 JNDI (Java Naming and Directory Interface) 特性测试
 *
 * JNDI (Java Naming and Directory Interface) 是 Java 命名和目录接口,
 * 为 Java 应用程序提供统一的命名和目录服务访问接口。
 *
 * 核心概念:
 * 1. 命名服务(Naming Service): 将名称与对象关联, 如 DNS、文件系统
 * 2. 目录服务(Directory Service): 在命名服务基础上, 允许对象有属性,
 *    如 LDAP 目录服务
 *
 * 核心类/接口 (javax.naming 包):
 * 1. Context: 命名上下文接口, 核心操作包括 bind、lookup、rebind、unbind
 * 2. InitialContext: Context 的初始实现, 用于创建命名上下文
 * 3. Name: 名称接口, 表示一个复合名称
 * 4. NamingException: 命名操作异常的基类
 *
 * 注意: 以下代码仅展示 JNDI 的代码结构和 API 用法, 不实际连接 JNDI 服务。
 * 实际使用时需要配置 JNDI 服务提供者(如 LDAP、DNS、RMI 注册表等)。
 *
 * @author lingwh
 * @date 2026/08/05 19:07
 */
public class JNDITest {

    /**
     * 测试 InitialContext 的基本用法
     *
     * InitialContext 是 JNDI 操作的入口点, 通过它可以执行查找、绑定、
     * 解绑等命名操作。创建 InitialContext 时需要指定 JNDI 服务提供者的
     * 环境属性。
     */
    @Test
    public void testInitialContextBasic() {
        System.out.println("========== InitialContext 基本用法 ==========");

        // 设置 JNDI 环境属性
        // 这些属性用于指定 JNDI 服务提供者、URL、认证信息等
        Hashtable<String, String> environment = new Hashtable<>();

        // 设置 JNDI 服务提供者工厂类
        // 不同的服务使用不同的工厂类, 例如:
        // - LDAP: com.sun.jndi.ldap.LdapCtxFactory
        // - DNS: com.sun.jndi.dns.DnsContextFactory
        // - RMI: com.sun.jndi.rmi.registry.RegistryContextFactory
        environment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.fscontext.RefFSContextFactory");

        // 设置服务提供者的 URL
        // 不同服务类型的 URL 格式:
        // - LDAP: ldap://localhost:389
        // - DNS: dns://dns-server
        // - RMI: rmi://localhost:1099
        // - 文件系统: file:///path
        environment.put(Context.PROVIDER_URL, "file:///tmp/jndi-test");

        // 设置安全凭证(如果需要认证)
        environment.put(Context.SECURITY_PRINCIPAL, "admin");
        environment.put(Context.SECURITY_CREDENTIALS, "password");

        // 注意: InitialContext 未实现 AutoCloseable 接口, 不能使用 try-with-resources,
        // 需要先声明为 null, 在 finally 块中手动调用 close() 释放资源
        InitialContext context = null;
        try {
            context = new InitialContext(environment);
            System.out.println("InitialContext 创建成功: " + context.getClass().getName());
            System.out.println("环境属性: " + environment);

            // 注意: 此处不实际执行 lookup 操作, 仅展示代码结构
            // 实际使用时需要确保 JNDI 服务已启动且可访问
            System.out.println("JNDI 上下文已创建, 可执行 lookup/bind 等操作");

        } catch (NamingException e) {
            // 捕获 NamingException 及其子类
            // 常见的子类异常:
            // - NameNotFoundException: 名称未找到
            // - InvalidNameException: 名称格式无效
            // - ServiceUnavailableException: 服务不可用
            // - AuthenticationException: 认证失败
            System.err.println("JNDI 操作失败: " + e.getClass().getSimpleName());
            System.err.println("异常信息: " + e.getMessage());
            // 实际应用中应记录日志, 这里仅打印异常信息
            // 由于没有实际 JNDI 服务, 此处预期会抛出异常
            System.out.println("(预期: 由于没有实际 JNDI 服务, 会抛出异常)");
        } finally {
            // 在 finally 中手动关闭 InitialContext, 释放资源
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    System.err.println("关闭 InitialContext 失败: " + e.getMessage());
                }
            }
        }
    }

    /**
     * 测试使用默认环境创建 InitialContext
     *
     * 如果不提供环境属性, InitialContext 会从 jndi.properties 文件
     * 或系统属性中读取默认配置。
     */
    @Test
    public void testInitialContextDefault() {
        System.out.println("========== 默认环境 InitialContext ==========");

        try {
            // 使用无参构造器创建 InitialContext
            // 它会从 classpath 下的 jndi.properties 文件中读取环境属性
            // 如果找不到配置文件, 会抛出 NoInitialContextException
            InitialContext context = new InitialContext();
            System.out.println("默认 InitialContext 创建成功");
            context.close();
        } catch (NamingException e) {
            System.err.println("默认 InitialContext 创建失败: " + e.getClass().getSimpleName());
            System.err.println("异常信息: " + e.getMessage());
            System.out.println("(预期: 由于没有 jndi.properties 配置, 会抛出异常)");
        }
    }

    /**
     * 测试 JNDI 的 lookup 操作
     *
     * lookup 是 JNDI 中最常用的操作, 用于根据名称查找绑定在命名空间中的对象。
     * 这里仅展示代码结构, 不实际执行。
     */
    @Test
    public void testLookup() {
        System.out.println("========== JNDI lookup 操作展示 ==========");

        // 展示 lookup 操作的典型代码结构
        // 实际使用时, 需要先创建 InitialContext, 然后调用 lookup 方法

        // 伪代码示例:
        // try {
        //     // 1. 创建 InitialContext
        //     InitialContext context = new InitialContext(environment);
        //
        //     // 2. 查找 JNDI 资源
        //     // 查找数据源
        //     DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mydb");
        //
        //     // 查找 EJB 组件
        //     Object ejb = context.lookup("java:comp/env/ejb/MyBean");
        //
        //     // 查找 JMS 连接工厂
        //     ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/ConnectionFactory");
        //
        //     // 3. 使用查找到的资源
        //     // Connection conn = dataSource.getConnection();
        //
        //     // 4. 关闭上下文
        //     context.close();
        // } catch (NamingException e) {
        //     e.printStackTrace();
        // }

        System.out.println("JNDI lookup 典型用法:");
        System.out.println("  1. 创建 InitialContext");
        System.out.println("  2. 调用 context.lookup(\"jndi/name\") 查找资源");
        System.out.println("  3. 将查找到的对象转换为目标类型");
        System.out.println("  4. 使用资源对象");
        System.out.println("  5. 关闭 InitialContext");

        System.out.println();
        System.out.println("常见的 JNDI 资源类型:");
        System.out.println("  - javax.sql.DataSource: 数据库连接池");
        System.out.println("  - javax.jms.ConnectionFactory: JMS 连接工厂");
        System.out.println("  - javax.ejb.EJBHome: EJB 组件");
        System.out.println("  - javax.mail.Session: JavaMail 会话");
    }

    /**
     * 测试 JNDI 的 bind/lookup/unbind 操作
     *
     * 展示 JNDI 命名操作的完整生命周期: 绑定 -> 查找 -> 解绑。
     * 由于可能没有实际 JNDI 服务, 使用 try-catch 处理异常。
     */
    @Test
    public void testBindLookupUnbind() {
        System.out.println("========== JNDI bind/lookup/unbind 操作展示 ==========");

        // 设置环境属性
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL, "file:///tmp/jndi-test");

        // InitialContext 未实现 AutoCloseable 接口, 需在 finally 中手动关闭
        InitialContext context = null;
        try {
            context = new InitialContext(env);
            // 1. bind: 将名称绑定到对象
            String name = "cn/test/hello";
            String value = "Hello, JNDI!";
            System.out.println("绑定名称: " + name + " -> " + value);
            context.bind(name, value);

            // 2. lookup: 通过名称查找对象
            Object lookupResult = context.lookup(name);
            System.out.println("查找结果: " + lookupResult);

            // 3. rebind: 重新绑定(覆盖已有绑定)
            String newValue = "Hello, JNDI Updated!";
            context.rebind(name, newValue);
            System.out.println("重新绑定后: " + context.lookup(name));

            // 4. unbind: 解绑(移除名称与对象的关联)
            context.unbind(name);
            System.out.println("已解绑名称: " + name);

            // 5. 检查名称是否存在
            // context.lookup(name); // 会抛出 NameNotFoundException

        } catch (NamingException e) {
            // 由于没有实际 JNDI 服务, 这里会抛出异常
            // 展示 NamingException 的异常处理方式
            System.err.println("JNDI 命名操作失败: " + e.getClass().getSimpleName());
            System.err.println("异常信息: " + e.getMessage());
            System.out.println("(预期: 由于没有实际 JNDI 服务, 会抛出异常)");
        } finally {
            // 在 finally 中手动关闭 InitialContext, 释放资源
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    System.err.println("关闭 InitialContext 失败: " + e.getMessage());
                }
            }
        }
    }

    /**
     * 测试 JNDI 子上下文操作
     *
     * JNDI 支持层次化的命名空间, 可以通过 subcontext 组织资源。
     */
    @Test
    public void testSubcontext() {
        System.out.println("========== JNDI 子上下文操作展示 ==========");

        // JNDI 的命名空间是层次化的, 类似于文件系统的目录结构
        // 例如:
        //   java:comp/env/jdbc/mydb
        //   java:comp/env/ejb/MyBean
        //   cn/test/hello

        System.out.println("JNDI 层次化命名空间示例:");
        System.out.println("  java:comp/env/");
        System.out.println("    ├── jdbc/");
        System.out.println("    │   ├── mydb (DataSource)");
        System.out.println("    │   └── testdb (DataSource)");
        System.out.println("    ├── ejb/");
        System.out.println("    │   └── MyBean (EJB)");
        System.out.println("    ├── jms/");
        System.out.println("    │   └── ConnectionFactory (ConnectionFactory)");
        System.out.println("    └── mail/");
        System.out.println("        └── Session (Session)");

        System.out.println();
        System.out.println("子上下文操作 API:");
        System.out.println("  - context.createSubcontext(String name): 创建子上下文");
        System.out.println("  - context.destroySubcontext(String name): 销毁子上下文");
        System.out.println("  - context.list(String name): 列出指定上下文中的绑定");
        System.out.println("  - context.listBindings(String name): 列出指定上下文中的绑定(包含对象)");

        // 伪代码示例:
        // InitialContext context = new InitialContext(env);
        // try {
        //     // 创建子上下文
        //     Context subContext = context.createSubcontext("cn/test");
        //
        //     // 在子上下文中绑定对象
        //     subContext.bind("hello", "Hello World");
        //
        //     // 在子上下文中查找对象
        //     Object obj = subContext.lookup("hello");
        //
        //     // 列出所有绑定
        //     NamingEnumeration<NameClassPair> list = context.list("cn/test");
        //     while (list.hasMore()) {
        //         NameClassPair pair = list.next();
        //         System.out.println(pair.getName() + ": " + pair.getClassName());
        //     }
        //
        //     // 销毁子上下文
        //     context.destroySubcontext("cn/test");
        // } finally {
        //     context.close();  // 手动关闭 InitialContext(未实现 AutoCloseable)
        // }
    }

    /**
     * 测试 JNDI 异常处理
     *
     * NamingException 是 JNDI 操作的根异常类, 它有多个子类来区分不同的异常场景。
     * 这里展示各种 NamingException 子类的含义。
     */
    @Test
    public void testNamingException() {
        System.out.println("========== JNDI 异常处理 ==========");

        System.out.println("NamingException 常见子类及其含义:");
        System.out.println("  1. NoInitialContextException: 无法创建 InitialContext");
        System.out.println("     - 原因: 未配置 Context.INITIAL_CONTEXT_FACTORY");
        System.out.println("  2. NameNotFoundException: 指定的名称未找到");
        System.out.println("     - 原因: lookup 了一个不存在的名称");
        System.out.println("  3. InvalidNameException: 名称格式无效");
        System.out.println("     - 原因: 名称不符合 JNDI 命名规范");
        System.out.println("  4. ServiceUnavailableException: 服务不可用");
        System.out.println("     - 原因: JNDI 服务提供者无法连接");
        System.out.println("  5. AuthenticationException: 认证失败");
        System.out.println("     - 原因: 安全凭证无效");
        System.out.println("  6. AuthenticationNotSupportedException: 不支持的认证方式");
        System.out.println("     - 原因: 服务提供者不支持指定的认证机制");
        System.out.println("  7. CannotProceedException: 无法继续执行操作");
        System.out.println("     - 原因: 操作无法完成, 包含更具体的异常原因");
        System.out.println("  8. LimitExceededException: 超出限制");
        System.out.println("     - 原因: 操作结果超出大小或时间限制");

        System.out.println();
        System.out.println("异常处理最佳实践: ");
        System.out.println("  1. 始终捕获 NamingException 而不是具体的子类");
        System.out.println("  2. 在 finally 块中手动调用 close() 关闭 InitialContext(未实现 AutoCloseable 接口)");
        System.out.println("  3. 记录详细的异常信息以便调试");
        System.out.println("  4. 对于非关键操作, 考虑优雅降级而不是直接失败");
    }
}