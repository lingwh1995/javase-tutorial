package org.bluebridge.lock_09_synchronized_object_header;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

/**
 * 注意：运行前要关闭指针压缩，添加如下vm参数即可：-XX:-UseCompressedOops
 *
 * 64位虚拟机中Object Header中markword结构
 * 参考博客 https://www.cnblogs.com/kuangdaoyizhimei/p/18422634
 *
 * |-----------------------------------------------------------------------------------------------------------------|
 * | Object Header(128bits) |
 * |-----------------------------------------------------------------------------------------------------------------|
 * | Mark Word(64bits) | Klass Word(64bits) | State |
 * |-----------------------------------------------------------------------------------------------------------------|
 * | unused:25|identity_hashcode:31|unused:1|age:4|biase_lock:1|lock:2 | OOP to
 * metadata object | Nomal |
 * |-----------------------------------------------------------------------------------------------------------------|
 * | thread:54| epoch:2 |unused:1|age:4|biase_lock:1|lock:2 | OOP to metadata
 * object | Biased |
 * |-----------------------------------------------------------------------------------------------------------------|
 * | ptr_to_lock_record:62 |lock:2 | OOP to metadata object | Lightweight Locked
 * |
 * |-----------------------------------------------------------------------------------------------------------------|
 * | ptr_to_heavyweight_monitor:62 |lock:2 | OOP to metadata object |
 * Heavyweight Locked |
 * |-----------------------------------------------------------------------------------------------------------------|
 * | |lock:2 | OOP to metadata object | Marked for GC |
 * |-----------------------------------------------------------------------------------------------------------------|
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ObjectHeaderTest {

    /**
     * 测试打印当前jvm参数、普通对象头信息、数组对象头信息
     */
    @Test
    public void testPrintObjectHeaderHelloWorld() {
        // 对象
        Object obj = new Object();
        // 数组
        String[] array = new String[] {};
        // 打印jvm的具体参数
        System.out.println(VM.current().details());
        System.out.println("---------------------------------------------------------------");

        // 打印普通对象头信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println("---------------------------------------------------------------");

        // 打印数组对象头信息
        System.out.println(ClassLayout.parseInstance(array).toPrintable());
        System.out.println("---------------------------------------------------------------");
    }

    /**
     * 查看计算hashcode前后 object header: mark 的值
     */
    @Test
    public void testPrintObjectHeaderBeforeAndAfterCalcHashcode() {
        // 对象
        Object obj = new Object();

        // 计算hashcode前打印普通对象头信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println("---------------------------------------------------------------");

        // 计算hashcode
        System.out.printf("十进制hashCode: %s，十六进制hashCode: %s%n", obj.hashCode(), Integer.toHexString(obj.hashCode()));
        System.out.println("---------------------------------------------------------------");

        // 计算hashcode后打印普通对象头信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    /**
     * 查看对象总大小
     */
    @Test
    public void testPrintObjectTotalSize() {
        // 对象
        Object object = new Object();
        System.out.println("Object total size: " + GraphLayout.parseInstance(object).totalSize() + " bytes");
    }
}
