package org.bluebridge.protobuf.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.bluebridge.protobuf.proto.ContactProto;

/**
 * @author lingwh
 * @desc 测试 Protobuf 测试联系人写入
 * @date 2025/11/3 20:57
 */
@Slf4j
public class ProtobufOfContactsWriteTest {

    public static void main(String[] args) throws IOException {
        ContactProto.Contacts.Builder contactsBuilder = ContactProto.Contacts.newBuilder();

        // 读取本地已经存在的 contacts.bin，反序列化出通讯录对象
        try {
            // 方式一：分两步处理
            /*
            ContactProto.Contacts contacts = ContactProto.Contacts.parseFrom(new FileInputStream("d:/contacts.bin"));
            contactsBuilder = contacts.toBuilder();
            */

            // 方式二：一步处理
            contactsBuilder.mergeFrom(new FileInputStream("d:/contacts.bin"));
        } catch (Exception e) {
            log.error("读取文件失败：{}", e.getMessage());
        }

        // 向通讯录中新增一个联系人
        contactsBuilder.addContacts(addPeppleInfo());

        // 序列化通讯录，将结果写入文件中
        FileOutputStream outputStream = new FileOutputStream("d:/contacts.bin");
        contactsBuilder.build().writeTo(outputStream);
        // 关闭流
        outputStream.close();
    }

    private static ContactProto.PeopleInfo addPeppleInfo() {
        ContactProto.PeopleInfo.Builder peopleBuilder = ContactProto.PeopleInfo.newBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------新增联系人--------------------");
        System.out.println("请输入联系人姓名：");
        String name = scanner.nextLine();
        peopleBuilder.setName(name);

        System.out.println("请输入联系人年龄：");
        int age = Integer.parseInt(scanner.nextLine());
        peopleBuilder.setAge(age);

        while (true) {
            System.out.println("请输入联系人手机号，只输入回车结束输入：");
            String phoneNumber = scanner.nextLine();
            if (phoneNumber.isEmpty()) {
                break;
            }
            ContactProto.Phone.Builder phoneBuilder = ContactProto.Phone.newBuilder();
            phoneBuilder.setNumber(phoneNumber);
            peopleBuilder.addPhones(phoneBuilder);
        }
        System.out.println("--------------------新增联系人结束--------------------");
        return peopleBuilder.build();
    }
}
