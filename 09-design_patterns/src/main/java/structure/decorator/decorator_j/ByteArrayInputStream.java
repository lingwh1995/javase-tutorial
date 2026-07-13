package structure.decorator.decorator_j;

/**
 * 字节数组输入流
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class ByteArrayInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("ByteArrayInputStream read...");
        return 0;
    }
}
