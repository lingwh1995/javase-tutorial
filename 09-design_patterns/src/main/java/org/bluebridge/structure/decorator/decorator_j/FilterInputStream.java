package org.bluebridge.structure.decorator.decorator_j;

/**
 * 装饰者
 *
 * @author lingwh
 * @date 2026/7/22 08:48
 */
public class FilterInputStream extends InputStream {

    protected InputStream in;

    protected FilterInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() {
        return in.read();
    }
}
