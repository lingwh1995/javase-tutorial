package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 缓冲输入流
 * @date 2026/7/9 00:00
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
