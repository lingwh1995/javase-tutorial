package org.bluebridge.section_01_jdk1.unit_04_jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDK 1.1 JDBC(Java Database Connectivity)测试
 *
 * JDK 1.1 引入 java.sql 包, 提供统一的数据库访问接口, 核心组件如下:
 * 1. DriverManager: 驱动管理器, 负责加载数据库驱动并创建数据库连接
 * 2. Connection: 数据库连接, 代表与数据库的一条会话通道
 * 3. Statement: 语句对象, 用于向数据库发送 SQL 语句
 * 4. ResultSet: 结果集, 封装 SQL 查询返回的数据行
 * JDBC 使用流程: 加载驱动 -> 获取连接 -> 创建 Statement -> 执行 SQL -> 处理结果集 -> 释放资源
 * 注意: 当前项目未引入任何数据库驱动, 本测试仅演示代码结构和流程, 不会实际连接数据库
 *
 * @author lingwh
 * @date 2026/08/05 18:26
 */
public class JdbcTest {

    // 数据库驱动类(以 MySQL 为例, 实际项目中根据数据库类型替换)
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    // 数据库连接地址(示例参数, 不会真正发起连接)
    private static final String URL = "jdbc:mysql://localhost:3306/test";

    // 数据库用户名
    private static final String USERNAME = "root";

    // 数据库密码
    private static final String PASSWORD = "root";

    /**
     * 测试 JDBC 第一步: 加载数据库驱动
     */
    @Test
    public void testLoadDriver() {
        try {
            // JDBC 1.1 时代通过 Class.forName 加载数据库驱动
            Class.forName(DRIVER);
            System.out.println("驱动加载成功: " + DRIVER);
        } catch (ClassNotFoundException e) {
            // 当前项目未引入 MySQL 驱动, 会进入该异常分支, 这里捕获异常说明驱动加载流程
            System.out.println("驱动加载失败, 未找到驱动类: " + DRIVER + ", 原因: " + e.getMessage());
        }
    }

    /**
     * 测试 JDBC 第二步: 通过 DriverManager 获取数据库连接(演示代码结构, 不实际连接)
     */
    @Test
    public void testGetConnection() {
        // 实际开发中通过 DriverManager.getConnection 获取连接, 代码如下:
        // Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        // 注意: 获取连接前必须先加载驱动, 使用完毕后必须关闭连接释放资源
        System.out.println("JDBC 连接流程: Class.forName 加载驱动 -> DriverManager.getConnection 获取连接");
        System.out.println("连接参数: url = " + URL + ", username = " + USERNAME + ", password = " + PASSWORD);
        System.out.println("说明: 当前环境没有数据库和驱动, 不执行真正的连接, 仅展示代码结构");
    }

    /**
     * 测试 JDBC 完整查询流程: Connection + Statement + ResultSet(演示代码结构, 不实际执行)
     */
    @Test
    public void testQueryAndResultSetFlow() {
        // 声明数据库资源对象
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 第一步: 加载驱动
            Class.forName(DRIVER);
            // 第二步: 获取连接(当前环境不实际连接, 故注释掉)
            // connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // 第三步: 创建 Statement 对象
            // statement = connection.createStatement();
            // 第四步: 执行 SQL 查询并返回结果集
            // resultSet = statement.executeQuery("select * from user");
            // 第五步: 遍历结果集处理数据
            // while (resultSet.next()) {
            //     String name = resultSet.getString("name");
            //     int age = resultSet.getInt("age");
            //     System.out.println("name = " + name + ", age = " + age);
            // }
            System.out.println("JDBC 查询流程共五步: 加载驱动 -> 获取连接 -> 创建 Statement -> 执行 SQL -> 处理结果集");
            System.out.println("以上连接、执行 SQL、处理结果集等代码在实际数据库环境下启用");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败: " + e.getMessage());
        } finally {
            // 第六步: 释放资源(按 resultSet -> statement -> connection 的顺序逆序关闭)
            closeQuietly(resultSet);
            closeQuietly(statement);
            closeQuietly(connection);
        }
    }

    /**
     * 静默关闭数据库资源, 避免资源泄漏
     */
    private void closeQuietly(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
