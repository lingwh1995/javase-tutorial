package structure.decorator.decorator_j;

/**
 * 序列输入流
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class SequenceInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("SequenceInputStream read...");
        return 0;
    }
}
