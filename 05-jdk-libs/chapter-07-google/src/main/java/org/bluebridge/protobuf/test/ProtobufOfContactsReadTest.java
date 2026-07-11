package org.bluebridge.protobuf.test;

import java.io.FileInputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.bluebridge.protobuf.proto.ContactProto;

/**
 * @author lingwh
 * @desc 测试 Protobuf 测试联系人读取
 * @date 2025/11/6 23:59
 */
@Slf4j
public class ProtobufOfContactsReadTest {

    public static void main(String[] args) throws IOException {
        // 读取文件，将内容进行反序列化
        FileInputStream inputStream = new FileInputStream("d:/contacts.bin");
        // 反序列化
        ContactProto.Contacts contacts = ContactProto.Contacts.parseFrom(inputStream);

        // 打印联系人信息
        // printContacts(contacts);
        log.info("通讯录：{}", contacts.toString());
    }

    private static void printContacts(ContactProto.Contacts contacts) {
        contacts.getContactsList().forEach(peopleInfo -> {
            log.info("姓名：{}，年龄：{}", peopleInfo.getName(), peopleInfo.getAge());
            peopleInfo.getPhonesList().forEach(phone -> {
                log.info("手机号：{}", phone.getNumber());
            });
            log.info("--------------------");
        });
    }
}
