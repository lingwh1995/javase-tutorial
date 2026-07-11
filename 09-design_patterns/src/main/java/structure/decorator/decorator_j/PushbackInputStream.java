package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 回退输入流
 * @date 2026/7/9 00:00
 */
public class PushbackInputStream extends FilterInputStream {

    protected PushbackInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() {
        super.read();
        System.out.println("读取的时候加入回退功能...");
        return 0;
    }
}
