package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 字节数组输入流
 * @date 2026/7/9 00:00
 */
public class ByteArrayInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("ByteArrayInputStream read...");
        return 0;
    }
}
