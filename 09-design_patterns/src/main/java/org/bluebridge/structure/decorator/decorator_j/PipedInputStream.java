package org.bluebridge.structure.decorator.decorator_j;

/**
 * 管道输入流
 *
 * @author lingwh
 * @date 2026/7/22 09:05
 */
public class PipedInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("PipedInputStream read...");
        return 0;
    }
}
