package org.bluebridge.structure.decorator.decorator_j;

/**
 * 文件输入流
 *
 * @author lingwh
 * @date 2026/7/22 10:11
 */
public class FileInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("FileInputStream read...");
        return 0;
    }
}
