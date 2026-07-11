package org.bluebridge.non_null;

import lombok.NonNull;

/**
 * @author lingwh
 * @desc UserDao，在方法参数(普通方法的参数、构造方法的参数)上和字段上使用@NonNull注解，可以快速便捷的参数传入判断空值的情况
 * @date 2025/8/18 13:52
 */
public class UserDao {
    public void deleteUserById(@NonNull String id) {}
}
