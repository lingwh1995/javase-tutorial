package org.bluebridge.structure.decorator.decorator_j;

/**
 * 数据输入流
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class DataInputStream extends FilterInputStream {

    protected DataInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() {
        super.read();
        System.out.println("将读取结果转换为指定的数据类型...");
        return 0;
    }
}
