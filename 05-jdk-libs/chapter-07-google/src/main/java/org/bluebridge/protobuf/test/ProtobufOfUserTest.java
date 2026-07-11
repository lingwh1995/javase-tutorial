package org.bluebridge.protobuf.test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.bluebridge.protobuf.proto.UserProto;

/**
 * @author lingwh
 * @desc 测试 Protobuf 用户
 * @date 2025/11/3 20:57
 */
@Slf4j
public class ProtobufOfUserTest {

    public static void main(String[] args) {
        // -------------- 1. 创建 User 对象（通过 Builder 模式）--------------
        UserProto.Address address = UserProto.Address.newBuilder()
                .setProvince("广东省")
                .setCity("深圳市")
                .setDetail("南山区科技园")
                .build(); // 构建 Address 对象

        UserProto.User user = UserProto.User.newBuilder()
                .setId(1001)
                .setName("张三")
                .setAge(25)
                .addTags("Java开发") // 给 repeated 字段添加元素（单个添加）
                .addTags("微服务")
                .addAllTags(Arrays.asList("Protobuf", "GRPC")) // 批量添加
                .setAddress(address) // 设置嵌套的 Address 对象
                .build(); // 构建 User 对象（不可变，后续无法修改字段）

        log.info("原始 User 对象：{}", user);
        // 输出示例：id:1001 name:"张三" age:25 tags:"Java开发" tags:"微服务" tags:"Protobuf" tags:"GRPC" address {
        // province:"广东省" city:"深圳市" detail:"南山区科技园" }

        // -------------- 2. 序列化：User 对象 → 二进制字节数组（用于传输/存储）--------------
        byte[] serializedBytes = user.toByteArray();
        log.info("序列化后的二进制数组长度：{}", serializedBytes.length);
        // 输出示例：长度约 40 字节（比 JSON 小很多，JSON 同等数据约 80 字节）

        // -------------- 3. 反序列化：二进制字节数组 → User 对象（接收方解析）--------------
        try {
            UserProto.User parsedUser = UserProto.User.parseFrom(serializedBytes);
            log.info("\n反序列化后的 User 对象：{}", parsedUser);
            log.info("ID：{}", parsedUser.getId());
            log.info("姓名：{}", parsedUser.getName());
            log.info("年龄：{}", parsedUser.getAge());
            log.info("标签：{}", parsedUser.getTagsList());
            log.info("地址：{}", parsedUser.getAddress().getProvince() + "-" + parsedUser.getAddress().getCity());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            System.err.println("反序列化失败：二进制数据格式错误");
        }

        // -------------- 可选：与 JSON 互转（调试用）--------------
        try {
            // User 对象 → JSON 字符串（需要引入 protobuf-java-util 依赖）
            String json = JsonFormat.printer().print(user);
            log.info("JSON 转 User：{}", json);

            // JSON 字符串 → User 对象
            UserProto.User.Builder userBuilder = UserProto.User.newBuilder();
            JsonFormat.parser().merge(json, userBuilder);
            UserProto.User jsonParsedUser = userBuilder.build();
            log.info("JSON 转 User：{}", jsonParsedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
