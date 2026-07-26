package org.bluebridge.structure.decorator.decorator_j;

/**
 * 字节数组输入流
 *
 * @author lingwh
 * @date 2026/7/22 08:32
 */
public class ByteArrayInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("ByteArrayInputStream read...");
        return 0;
    }
}
