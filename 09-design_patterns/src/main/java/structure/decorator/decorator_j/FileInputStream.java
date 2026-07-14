package structure.decorator.decorator_j;

/**
 * 文件输入流
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class FileInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("FileInputStream read...");
        return 0;
    }
}
