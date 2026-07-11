package org.bluebridge.non_null;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 测试@NonNull注解
 * @date 2025/8/18 13:54
 */
public class NonNullAnnoTest {

    @Test
    public void testNonNullAnno() {
        UserDao userDao = new UserDao();
        userDao.deleteUserById(null);
    }
}
