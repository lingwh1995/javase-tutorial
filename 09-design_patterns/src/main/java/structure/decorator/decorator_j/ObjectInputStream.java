package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 对象输入流
 * @date 2026/7/9 00:00
 */
public class ObjectInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("ObjectInputStream read...");
        return 0;
    }
}
