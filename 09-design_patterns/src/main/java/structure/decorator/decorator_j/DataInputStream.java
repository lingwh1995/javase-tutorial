package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 数据输入流
 * @date 2026/7/9 00:00
 */
public class DataInputStream extends FilterInputStream {

    protected DataInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() {
        super.read();
        System.out.println("将读取结果转换为指定的数据类型...");
        return 0;
    }
}
