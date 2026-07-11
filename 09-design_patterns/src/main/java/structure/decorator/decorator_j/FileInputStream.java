package structure.decorator.decorator_j;

/**
 * @author lingwh
 * @desc 文件输入流
 * @date 2026/7/9 00:00
 */
public class FileInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("FileInputStream read...");
        return 0;
    }
}
