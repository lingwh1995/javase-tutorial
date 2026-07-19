package action.strategy.strategy_e.dao;

import java.io.IOException;

/**
 * 用户数据访问
 *
 * @author lingwh
 * @date 2019/8/5 9:07
 */
public class UserDao {

    public void save(String msg) throws IOException {
        System.out.println("把数据: " + msg + " 保存到数据库......");
        // 模拟抛出一个IO异常
        throw new IOException();
    }
}
