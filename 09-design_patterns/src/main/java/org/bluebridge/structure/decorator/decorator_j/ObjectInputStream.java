package org.bluebridge.structure.decorator.decorator_j;

/**
 * 对象输入流
 *
 * @author lingwh
 * @date 2026/7/22 10:28
 */
public class ObjectInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("ObjectInputStream read...");
        return 0;
    }
}
