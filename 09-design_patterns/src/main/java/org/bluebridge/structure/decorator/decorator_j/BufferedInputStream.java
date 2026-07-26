package org.bluebridge.structure.decorator.decorator_j;

/**
 * 缓冲输入流
 *
 * @author lingwh
 * @date 2026/7/22 10:45
 */
public class BufferedInputStream extends FilterInputStream {

    protected BufferedInputStream(InputStream in) {
        super(in);
    }

    public int read() {
        super.read();
        System.out.println("读取过程中加入缓存....");
        return 0;
    }
}
