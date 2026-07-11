package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 管道输入流
 * @date 2026/7/9 00:00
 */
public class PipedInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("PipedInputStream read...");
        return 0;
    }
}
