package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 装饰者
 * @date 2026/7/9 00:00
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
