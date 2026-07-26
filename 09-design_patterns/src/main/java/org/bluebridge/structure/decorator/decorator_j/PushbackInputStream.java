package org.bluebridge.structure.decorator.decorator_j;

/**
 * 回退输入流
 *
 * @author lingwh
 * @date 2026/7/22 09:38
 */
public class PushbackInputStream extends FilterInputStream {

    protected PushbackInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() {
        super.read();
        System.out.println("读取的时候加入回退功能...");
        return 0;
    }
}
