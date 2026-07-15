package org.bluebridge.hsqldb;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.*;

/**
 * 测试hsqldb基本功能
 *
 * @author lingwh
 * @date 2025/8/18 14:20
 */
@Slf4j
public class HsqldbTest {

    @Test
    public void testHsqldb() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:hsqldb", "root", "root");
            Statement stat = conn.createStatement();
            // 建表
            String createTableSql = "create table t_user (id integer, name varchar(512), createtime timestamp default now())";
            stat.executeUpdate(createTableSql);

            // 插入数据
            String insertSql = "insert into t_user (id,name) values(1,'张三'),(2,'李四')";
            stat.executeUpdate(insertSql);
            insertSql = "insert into t_user values(3,'王五',now())";
            stat.executeUpdate(insertSql);

            // 查询
            int param = 3;
            String querySql = "select * from t_user where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(querySql);
            preparedStatement.setInt(1, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date date = resultSet.getDate("createtime");
                Time time = resultSet.getTime("createtime");
                log.info("id: {}, name: {}, date: {}, time: {}", id, name, date, time);
            }

            // 修改
            PreparedStatement preparedStatement2 = conn.prepareStatement("update t_user set name = ? where id = ?");
            preparedStatement2.setString(1, "hsqldb");
            preparedStatement2.setInt(2, 3);
            int update = preparedStatement2.executeUpdate();
            log.info("update: {}", update);

            // 查询列表
            querySql = "select * from t_user";
            PreparedStatement preState = conn.prepareStatement(querySql);
            boolean result = preState.execute();
            if (result) {
                ResultSet resultset = preState.getResultSet();
                while (resultset.next()) {
                    int id = resultset.getInt("id");
                    String name = resultset.getString("name");
                    Date date = resultset.getDate("createtime");
                    String time = resultset.getTime("createtime").toString();
                    log.info("id: {}, name: {}, data: {}, time: {}", id, name, date, time);
                }
            }

            // 删除
            PreparedStatement preState2 = conn.prepareStatement("delete from t_user where id = ?");
            preState2.setInt(1, 3);
            int delete = preState2.executeUpdate();
            log.info("delete: {}", delete);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
