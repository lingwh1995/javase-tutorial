package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 序列输入流
 * @date 2026/7/9 00:00
 */
public class SequenceInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("SequenceInputStream read...");
        return 0;
    }
}
