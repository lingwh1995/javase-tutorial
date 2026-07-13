package structure.decorator.decorator_j;

/**
 * 缓冲输入流
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
